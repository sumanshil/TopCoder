package com.topcoder.problems.round162;

//http://community.topcoder.com/stat?c=problem_statement&pm=1846&rd=4615
public class PaperFold
{
	int min = Integer.MAX_VALUE;
	public int numFolds(int[] paper, int[] box)
	{
		float[] paperFloat = new float[paper.length];
		float[] boxFloat = new float[box.length];
		for(int i = 0 ; i < paper.length ; i++)
		{
			paperFloat[i] = (float)paper[i];
			boxFloat[i]   = (float)box[i];
		}
		
		recursiveUtil(paperFloat,
				      boxFloat,
				      0);
		return min == Integer.MAX_VALUE ? -1 : min;
	}
	
	
	
	private void recursiveUtil(float[] paper,
			                   float[] box,
			                   int foldCount)
	{
		if (foldCount > 8)
		{
			return;
		}
		else if (paper[0] <=box[0]
				&& paper[1] <= box[1])
		{
			if (foldCount < min)
			{
				min = foldCount;
			}
			return;
		}
		else if (paper[1] <=box[0]
				&& paper[0] <= box[1])
		{
			if (foldCount < min)
			{
				min = foldCount;
			}
			return;			
		}
		float originalWidth = paper[0];
		float originalLength = paper[1];
		if (originalWidth > box[0]
				|| originalWidth > box[1])
		{
		    paper[0] = (float)originalWidth/2;
		    paper[1] = originalLength;		
		    recursiveUtil(paper, box, foldCount+1);
	    }
		
		if (originalLength > box[0]
				|| originalLength > box[1])
		{
		    paper[0] = originalWidth;
		    paper[1] = (float)originalLength/2;
		    recursiveUtil(paper, box, foldCount+1);
		}
	}



	public static void main(String[] args)
	{
		int result = new PaperFold().numFolds(new int[]{1895, 6416},
				                 new int[]{401, 1000});
		System.out.println(result);

	}

}
