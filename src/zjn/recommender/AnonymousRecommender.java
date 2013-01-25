package zjn.recommender;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.PlusAnonymousUserDataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

public class AnonymousRecommender extends UserBasedRecommender {
	
	 private final PlusAnonymousUserDataModel plusAnonymousModel;

	public AnonymousRecommender() throws TasteException {
		super();
		plusAnonymousModel = (PlusAnonymousUserDataModel) super.getDataModel();
	}
	
	 public  synchronized List<RecommendedItem> recommend(long userID, int howMany)throws TasteException {
			
		    PreferenceArray anonymousPrefs = new GenericUserPreferenceArray(3);
		    anonymousPrefs.setUserID(0,PlusAnonymousUserDataModel.TEMP_USER_ID);
		    anonymousPrefs.setItemID(0, 50L);
		    anonymousPrefs.setValue(0, 1.0f);
		    anonymousPrefs.setItemID(1, 50L);
		    anonymousPrefs.setValue(1, 3.0f);
		    anonymousPrefs.setItemID(2, 50L);
		    anonymousPrefs.setValue(2, 2.0f); 
		    plusAnonymousModel.setTempPrefs(anonymousPrefs);
		    List<RecommendedItem> recommendations = recommend(PlusAnonymousUserDataModel.TEMP_USER_ID, howMany, null);
		   
		    plusAnonymousModel.clearTempPrefs();
		    return recommendations;
		  }
	
	
}
