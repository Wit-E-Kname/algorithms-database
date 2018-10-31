#include<bits/stdc++.h>

using namespace std;

typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int, int> pii;

// priority queue implementation
// cannot remove from pq, so ingnore
/* int djikstra(int n, vvi& adj, int start, int end) {
	// cout << "djikstra";
	vi dist(n, INT_MAX);
	vi visited(n, 0);
	dist[start] = 0; visited[start] = 1;
	// set<int> s;
	// s.push(start);
	priority_queue<pair<int, int>> pq;
	pq.push(make_pair(0, start));

	while(!pq.empty()) {
		int u = pq.top().second; pq.pop();
		
		for(auto v=0; v<n; v++) {
			if(adj[v][u] == 0) continue; // no edge
			if(dist[v] > dist[u] + adj[v][u]) {
				dist[v] = dist[u] + adj[v][u];
				pq.push(make_pair(dist[v], v));
			}
		}
	}
	return dist[end];
} */

int djikstra(int n, vvi& adj, int start, int end) {
	vi dist(n, INT_MAX);
	vi parent(n, start);
	set<pii> s;
	s.insert(make_pair(0, start));
	dist[start] = 0;
	parent[start] = -1;

	while(!s.empty()) {
		pii top = *(s.begin());
		s.erase(s.begin());

		int u = top.second;
		for(auto v=0; v<n; v++) {
			if(adj[u][v] == 0) continue; // no edge

			if(dist[v] > dist[u] + adj[u][v]) {
				s.erase({dist[v], v});
				dist[v] = dist[u] + adj[u][v];
				parent[v] = u;
				s.insert(make_pair(dist[v], v));
			}
		}
	}
	vi ans;
	int padre = end;
	while(padre!=-1) {
		ans.push_back(padre);
		padre = parent[padre];
	}
	reverse(ans.begin(), ans.end());
	cout << dist[end] << endl;
	
	// path
	for(auto i: ans) {cout << i << " "; }
	cout << endl;
}


int main() {
	int t, n, start, end;
	cin >> t;
	while(t--) {
		cin >> n;
		vvi adj(n, vi(n, 0));
		for(auto i = 0; i < n; i++) {
			for(auto j=0; j < n; j++) {
				cin >> adj[i][j];
			}
		}
		cin >> start >> end;
		djikstra(n, adj, start, end);
	}
	return 0;
}