package zjn.recommender;

import java.util.Collection;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class GenderItemSimilarity implements ItemSimilarity {

	 private final FastIDSet men;
	 private final FastIDSet women;
	  
	 public GenderItemSimilarity(FastIDSet men, FastIDSet women) {
		    this.men = men;
		    this.women = women;
		  }
	

	@Override
	public double itemSimilarity(long profileID1, long profileID2) throws TasteException {
		  Boolean profile1IsMan = isMan(profileID1);
		    if (profile1IsMan == null) {
		      return 0.0;
		    }
		    Boolean profile2IsMan = isMan(profileID2);
		    if (profile2IsMan == null) {
		      return 0.0;
		    }
		    return profile1IsMan == profile2IsMan ? 1.0 : -1.0;
	}
	
	 public double[] itemSimilarities(long itemID1, long[] itemID2s) {
	    double[] result = new double[itemID2s.length];
	   
	      try {
	    	  for (int i = 0; i < itemID2s.length; i++) {
			       result[i] = itemSimilarity(itemID1, itemID2s[i]);
	    	    }
	    	  return result;
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    return null;
	  }
	
	 public long[] allSimilarItemIDs(long itemID) {
		    throw new UnsupportedOperationException();
		  }

		  private Boolean isMan(long profileID) {
		    if (men.contains(profileID)) {
		      return Boolean.TRUE;
		    }
		    if (women.contains(profileID)) {
		      return Boolean.FALSE;
		    }
		    return null;
		  }
	
	@Override
	public void refresh(Collection<Refreshable> arg0) {
	
	}

}
