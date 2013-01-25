package zjn.util;

import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.Recommender;

import zjn.recommender.ItemBasedRecommender;
import zjn.recommender.SlopOneRecommender;
import zjn.recommender.UserBasedRecommender;

public class FactoryBean {
	
	private static Recommender recommender;
	
	private FactoryBean(){
		
	}
	
	public static Recommender createRecommender(int i) throws TasteException, IOException{
		if(i==1){
			recommender = new UserBasedRecommender();
			return recommender;
		}else if(i==2){
			recommender = new ItemBasedRecommender();
			return recommender;
		}else if(i==3){
			recommender = new SlopOneRecommender();
			return recommender;
		}
		return null;
	}
	
	public static Recommender getRecommender(){
		return recommender;
	}

}
