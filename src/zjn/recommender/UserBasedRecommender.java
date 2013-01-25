package zjn.recommender;


import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import zjn.dao.PrefileDao;
import zjn.model.Prefile;

public class UserBasedRecommender implements Recommender {
	
	private final Recommender recommender;
	private final DataModel model;
	private final FastIDSet men;
	private final FastIDSet women;
	private final FastIDSet usersRateMoreMen;
	private final FastIDSet usersRateLessMen;
    //public static PrefileDao prefileDao = new PrefileDao();
	
	public UserBasedRecommender() throws TasteException{
		this(new MovieDataModel(),PrefileDao.getAllPrefile());
		
	}
	
	  public UserBasedRecommender(DataModel model,List<Prefile> prefileList) throws TasteException {
		 List<Prefile> prefiles = prefileList;
		//UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(model);
		UserSimilarity userSimilarity = new EuclideanDistanceSimilarity(model);
		//userSimilarity.setPreferenceInferrer(new AveragingPreferenceInferrer(model));
		UserNeighborhood neighborhood =
			  new NearestNUserNeighborhood(3, userSimilarity, model);
		
		recommender = new CachingRecommender(new GenericUserBasedRecommender(model, neighborhood, userSimilarity));
		this.model = model;
	    FastIDSet[] menWomen = GenderRescorer.parseMenWomen(prefiles);
	    men = menWomen[0];
	    women = menWomen[1];
	    usersRateMoreMen = new FastIDSet(1000);
	    usersRateLessMen = new FastIDSet(1000);
	  }

	@Override
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		recommender.refresh(alreadyRefreshed);
		
	}

	@Override
	public float estimatePreference(long userID, long itemID) throws TasteException {
		IDRescorer rescorer = new GenderRescorer(
		        men, women, usersRateMoreMen, usersRateLessMen, userID, model);
		    return (float) rescorer.rescore(
		        itemID, recommender.estimatePreference(userID, itemID));
	}

	@Override
	public DataModel getDataModel() {
		 return recommender.getDataModel();
	}

	@Override
	public List<RecommendedItem> recommend(long userID, int howMany)
			throws TasteException {
		IDRescorer rescorer = new GenderRescorer(men, women, usersRateMoreMen, usersRateLessMen, userID, model);
		return recommender.recommend(userID, howMany,rescorer);
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

}
