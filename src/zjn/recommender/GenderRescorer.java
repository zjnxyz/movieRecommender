package zjn.recommender;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.IDRescorer;

import zjn.model.Prefile;
public class GenderRescorer implements IDRescorer {
	
	  private final FastIDSet men;//存放当前数据模型对应的所有male selectableUser
	  private final FastIDSet women;//存放当前数据模型对应的所有female selectableUser
	  private final FastIDSet usersRateMoreMen;
	  private final FastIDSet usersRateLessMen;
	  private final boolean filterMen;//表明针对一个用户（userID定义）一个profileID是否应该过滤

	  public GenderRescorer(FastIDSet men,
	                        FastIDSet women,
	                        FastIDSet usersRateMoreMen,
	                        FastIDSet usersRateLessMen,
	                        long userID, DataModel model)
	      throws TasteException {
	    this.men = men;
	    this.women = women;
	    this.usersRateMoreMen = usersRateMoreMen;
	    this.usersRateLessMen = usersRateLessMen;
	    this.filterMen = ratesMoreMen(userID, model);
	  }
	 
	//产生数据对应的men和women集合(用来判断哪部电影更受男性或者女性喜欢）
	public static FastIDSet[] parseMenWomen(List<Prefile> prefiles){
		FastIDSet men = new FastIDSet(1000);
	    FastIDSet women = new FastIDSet(1000);
	    for(Prefile p:prefiles){
	    	if("u".equals(p.getSex())){
	    		continue;
	    	}
	    	
	    	if("m".equals(p.getSex())){
	    		men.add(p.getMovieID());
	    	}else{
	    		women.add(p.getMovieID());
	    	}
	    }
	    
	    men.rehash();
	    women.rehash();
	    return new FastIDSet[] { men, women };
	}
    
	 //判断userID对应的用户是不是更喜欢男性，从他/她评过分的那些用户的性别来统计
	 //根据历史性数据统计，即movie_preferences表中进行统计，得出是男的更喜欢该电影还是女的
	 //PreferenceArray里存储的是某一个用，所有喜欢的物品，从中可以推断出该用户是更加的女性化，还男性化
	private boolean ratesMoreMen(long userID, DataModel model) {
		 if (usersRateMoreMen.contains(userID)) {
		      return true;
		    }
		    if (usersRateLessMen.contains(userID)) {
		      return false;
		    }
		    PreferenceArray prefs;
			try {
				prefs = model.getPreferencesFromUser(userID);
				int menCount = 0;
			    int womenCount = 0;
			    for (int i = 0; i < prefs.length(); i++) {
			      long profileID = prefs.get(i).getItemID();
			      if (men.contains(profileID)) {
			        menCount++;
			      } else if (women.contains(profileID)) {
			        womenCount++;
			      }
			    }
			    
			    boolean ratesMoreMen = menCount > womenCount;
			    if (ratesMoreMen) {
			      usersRateMoreMen.add(userID);
			    } else {
			      usersRateLessMen.add(userID);
			    }
			    return ratesMoreMen;
			} catch (TasteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return false;
		  
	}

	//对于需要过滤的推荐，设置其值为NaN，这是因为他们不是不能推荐的，而是最差的推荐
	  @Override
	  public double rescore(long profileID, double originalScore) {
	    return isFiltered(profileID) ? Double.NaN : originalScore;
	  }

	//如果一个用户是喜欢男性的，而推荐的又是女性，则这个推荐是应该过滤掉的，反之亦然
	  @Override
	  public boolean isFiltered(long profileID) {
	    return filterMen ? men.contains(profileID) : women.contains(profileID);
	  }

}
