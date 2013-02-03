
public class diks {
	//dikjstra


	public void computeShortestRoute(Airport vertex, int aT,boolean AorP){

		GMTtime arriveTime = new GMTtime(aT,vertex.GMT,AorP);

		vertex.tracker = pq.putTracked(0, vertex);

		ArrayList<Airport> airports = new ArrayList<Airport>();

		for (Airport v: mappingTwo.keySet())

			airports.add(v);

		airports.remove(vertex);

		for (Airport v: airports)

			v.tracker = pq.putTracked(Integer.MAX_VALUE, v);

		for (Airport v: mappingTwo.keySet())

			v.parent = null;

		while(!pq.isEmpty()){

			Integer tag = (Integer) pq.min();

			if (tag == Integer.MAX_VALUE)

				return;

			Airport U = pq.extractMin();

			U.dist=tag;

			for (Flight f:U.getOutEdges(mappingTwo)){

				Airport dest = f.destAirport;



				//4 Conditions to check: Import parts are intermediate parts

				// where you have to wait at least 60 minutes

				if (dest.tracker.inCollection()&&

						U.dist+f.weight()+f.tD.minutesSince(arriveTime)-U.dist<(Integer)dest.tracker.tag&&

						// once arrive, if transfer, wait at least 60 minutes

						f.tD.minutesSince(arriveTime)>=U.dist+60){

					dest.tracker.update(U.dist+f.weight()+f.tD.minutesSince(arriveTime)-U.dist);

					dest.parent = f;

				}

			}

		}

	}
}
