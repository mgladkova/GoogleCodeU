import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QueryStream implements Iterable<String>{
	List<Query> list;
	MyIterator it;
	
	public static class MyIterator implements Iterator<String>{
		
		private final QueryStream stream;
		// current query to point to
		private Query current;
		// index of the word in Query
		private int index;
		// index of the query in Querystream
		private int qIndex;
		// the current split query
		private List <String> parts;
		// keep track of the previous query time
		private long timeQuery;
		
		/**
		 * Initialize the iterator
		 * @param str	Querystream to go through
		 */
		MyIterator(QueryStream str){
			current = str.list.get(0);
			stream = str;
			index = 0;
			qIndex = 0;
			timeQuery = 0;
			parts = new ArrayList<String>();
		}
		
		/**
		 * Return whether there are more words in query
		 */
		@Override
		public boolean hasNext() {
			// reinitialize the parameters for query
			if (index >= parts.size()){
				index = 0;
				parts.clear();
				return false;
			}
				
			return true; 
		}
		
		/**
		 * Return the next word in query
		 * 
		 */
		@Override
		public String next() {
			// if the current query is exhausted
			if (!hasNext()){
				// if not the end of the current Querystream
				qIndex++;
				if (qIndex < stream.list.size()){
					//System.out.println("<NEWQUERY>");
					
					// point to the next query
					current = stream.list.get(qIndex);
					long diff = current.time.getTime() - timeQuery;
					System.out.println("Since the last query: " + diff);
					timeQuery = current.time.getTime();
					
					// words of the current query separated by spaces
					String[] part = current.sentence.split(" ");
					for (String s: part){
						parts.add(s);
					}
				} else {
					// the end of Querystream
					return null;
				}
			} 
			// next word in query
			return parts.get(index++);
		}

		@Override
		public void remove() {
			return;
		}
		
	}
	
	/**
	 * Return the iterator if called
	 */
	@Override
	public Iterator<String> iterator() {
		return it;
	}
	
}

class Query{
	Timestamp time;
	String sentence;
}