package zjn.recommender;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.GenericItemSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import zjn.dao.MovieSimilarityDao;


public class ItemBasedRecommender implements Recommender {
	
	private final Recommender recommender;
	
	public ItemBasedRecommender() throws IOException, TasteException {
		  this(new MovieDataModel());
	  }
	
	  public ItemBasedRecommender(DataModel dataModel) throws TasteException {
		  //Collection<GenericItemSimilarity.ItemItemSimilarity> correlations = MovieSimilarityDao.getAllMovieSimilarities();
		  //ItemSimilarity itemSimilarity = new GenericItemSimilarity(correlations);
		  ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
		  recommender = new CachingRecommender(new EmbededItemBasedRecommender(new GenericItemBasedRecommender(dataModel, itemSimilarity)));
	  }

	@Override
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		recommender.refresh(alreadyRefreshed);
	}

	@Override
	public float estimatePreference(long userID, long itemID) throws TasteException {
		return recommender.estimatePreference(userID, itemID);
	}

	@Override
	public DataModel getDataModel() {
		return recommender.getDataModel();
	}

	@Override
	public List<RecommendedItem> recommend(long userID, int howMany)
			throws TasteException {
		return recommender.recommend(userID, howMany);
	}

	@Override
	public List<RecommendedItem> recommend(long userID, int howMany, IDRescorer rescorer)
			throws TasteException {
		return recommender.recommend(userID, howMany, rescorer);
	}

	@Override
	public void removePreference(long userID, long itemID) throws TasteException {
		recommender.removePreference(userID, itemID);

	}

	@Override
	public void setPreference(long userID, long itemID, float value)
			throws TasteException {
		recommender.setPreference(userID, itemID, value);
	}
	
	
	private static final class EmbededItemBasedRecommender implements Recommender {

	    private final GenericItemBasedRecommender recommender;

	    private EmbededItemBasedRecommender(GenericItemBasedRecommender recommender) {
	      this.recommender = recommender;
	    }

		public float estimatePreference(long userID, long itemID)
				throws TasteException {
			return recommender.estimatePreference(userID, itemID);
		}

		public DataModel getDataModel() {
			return recommender.getDataModel();
		}

		public List<RecommendedItem> recommend(long userID, int howMany)
				throws TasteException {
			return this.recommend(userID, howMany, null);
		}


		public void removePreference(long userID, long itemID)
				throws TasteException {
			recommender.removePreference(userID, itemID);
			
		}

		public void setPreference(long userID, long itemID, float value)
				throws TasteException {
			recommender.setPreference(userID, itemID, value);
			
		}

		public void refresh(Collection<Refreshable> alreadyRefreshed) {
			recommender.refresh(alreadyRefreshed);
		}

		@Override
		public List<RecommendedItem> recommend(long userID, int howMany,
				IDRescorer rescorer) throws TasteException {
			FastIDSet itemIDs = recommender.getDataModel().getItemIDsFromUser(userID);
			return recommender.mostSimilarItems(itemIDs.toArray(), howMany, null);
		}
	  }	  

}
