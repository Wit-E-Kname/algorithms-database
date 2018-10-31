#include <bits/stdc++.h>
#define max3(a,b,c) (max(max(a,b),c))
#define min3(a,b,c) (min(min(a,b),c))
#define pb push_back
#define mp make_pair
#define all(c) (c).begin(),(c).end()
#define allr(c) (c).rbegin(),(c).rend()
#define asort(c) sort(all(c))
#define dsort(c) sort(allr(c))
#define tr(c,i) for(typeof((c).begin()) i = (c).begin(); i != (c).end(); i++)
#define present(c,x) ((c).find(x) != (c).end())
#define cpresent(c,x) (find(all(c),x) != (c).end())
#define REP(i, a, b) for (int i = a; i <= b; i++)
#define FOR(i, n) for (int i = 0; i < n; i++)
#define ff first
#define ss second
#define BOOST ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL)
#define kickout(cs, ans) cout << "Case #" << cs << ": " << ans << "\n"
 
using namespace std;
typedef long long int ll;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int,int> pii;
typedef pair<int,ll> pill;
typedef vector<pii> vpii;

int main() {
	BOOST;
	int t, printcount = 1; cin >> t;
	while(t--) {
		int n; cin >> n;
		vector<int> A(n);
		for(int i=0; i < n; i++) cin >> A[i];

		dsort(A);
		ll ans = 0, count0 = 0;
		for(auto i: A) count0 += i==0;

		for(int i=0; i < n; i++) {
			unordered_map<int, int> map;
			map.clear();
			for(int j=0; j < i; j++) {
				ll prod =A[i]*A[j];
				// if(prod > A[0])  break;
				// if(A[j]==0) {
				// 	ans+= (count0-1)*count0 * (n-count0) / 2;
				// continue;
				// }
				if(map.find(prod)!=map.end()) {
					ans += map[prod];
				}
				map[A[j]]++;
			}
		}
		ll temp = (count0 * count0 - count0) / 2LL;
		ans += temp * (n - count0);
		kickout(printcount, ans);
		printcount++;
	}
	return 0;
}