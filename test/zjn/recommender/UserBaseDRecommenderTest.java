package zjn.recommender;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.junit.Test;


public class UserBaseDRecommenderTest {
	private Recommender recommender;
	@Test
	public void testRecommender(){
		try {
			recommender = new UserBasedRecommender();
			List<RecommendedItem> items  =  recommender.recommend(1, 20);
			for(RecommendedItem item:items){
				System.out.println(item.getItemID()+":"+item.getValue());
			}
		} catch (TasteException e) {
			e.printStackTrace();
		}
	}

}
