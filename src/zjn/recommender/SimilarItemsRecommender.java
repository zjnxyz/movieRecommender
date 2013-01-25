package zjn.recommender;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.Rescorer;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.common.LongPair;



public class SimilarItemsRecommender implements Recommender{
	
	private final  GenericItemBasedRecommender recommender ;
	
	public SimilarItemsRecommender() throws IOException, TasteException {
		  this(new MovieDataModel());
	  }
	
	  public SimilarItemsRecommender(MovieDataModel movieDataModel) {
		//Collection<GenericItemSimilarity.ItemItemSimilarity> correlations = MovieSimilarityDao.getAllMovieSimilarities();
		  //ItemSimilarity itemSimilarity = new GenericItemSimilarity(correlations);
		  ItemSimilarity itemSimilarity = new LogLikelihoodSimilarity(movieDataModel);
		  recommender = new GenericItemBasedRecommender(movieDataModel,itemSimilarity);
		 
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
	public List<RecommendedItem> recommend(long itemID, int howMany)
			throws TasteException {
		return recommender.mostSimilarItems(itemID, howMany);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RecommendedItem> recommend(long itemID, int howMany, IDRescorer rescorer)
			throws TasteException {
		return recommender.mostSimilarItems(itemID, howMany, (Rescorer<LongPair>) rescorer);
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
}
