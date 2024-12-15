	class Solution {
		public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
			Map<String, Double> has = new HashMap<>();
			has.put(initialCurrency, 1.0);
			{
				Queue<String> q = new ArrayDeque<>();
				q.add(initialCurrency);
				while (!q.isEmpty()) {
					String cur = q.poll();
					for (int i = 0; i < pairs1.size(); i++) {
						List<String> pair = pairs1.get(i);
						if (pair.get(0).equals(cur)) {
							String next = pair.get(1);
							double rate = rates1[i];
							if (!has.containsKey(next)) {
								has.put(next, has.get(cur) * rate);
								q.add(next);
							}
						} else if (pair.get(1).equals(cur)) {
							String next = pair.get(0);
							double rate = 1.0 / rates1[i];
							if (!has.containsKey(next)) {
								has.put(next, has.get(cur) * rate);
								q.add(next);
							}
						}
					}
				}
			}

			double max = 1;
			for(String k : has.keySet()) {
				Map<String, Double> nex = new HashMap<>();
				nex.put(k, has.get(k));
				Queue<String> q = new ArrayDeque<>();
				q.add(k);
				while(!q.isEmpty()){
					String cur = q.poll();
					for(int i = 0;i < pairs2.size();i++){
						List<String> pair = pairs2.get(i);
						if(pair.get(0).equals(cur)){
							String next = pair.get(1);
							double rate = rates2[i];
							if(!nex.containsKey(next)){
								nex.put(next, nex.get(cur) * rate);
								q.add(next);
							}
						}else if(pair.get(1).equals(cur)){
							String next = pair.get(0);
							double rate = 1.0 / rates2[i];
							if(!nex.containsKey(next)){
								nex.put(next, nex.get(cur) * rate);
								q.add(next);
							}
						}
					}
				}
				max = Math.max(max, nex.getOrDefault(initialCurrency, 0.0));
			}
			return max;
		}
	}