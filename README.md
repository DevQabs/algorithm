# 알고리즘


## **Graph**
----
그래프는 트리와 비슷하게 노드와 엣지로 구성되어 있습니다.(정확히는 트리가 그래프의 한 종류)<br/>그래프에서는 노드를 `버텍스`, 엣지를 `아크`라고 부릅니다. <br/>다른 버텍스로부터 오는 아크의 개수를 `In-degree`, 다른 버텍스로 가는 아크의 개수를 `Out-degree`라고 부릅니다. <br/>또한 방향성의 여부에 따라 `방향 그래프`와 `무방향 그래프`로 나뉩니다.

---
### **1. Breadth First Search(BFS)** <br/>

생성된 순서에 따라 노드를 확장한다.<br/>
시작점을 출발로 `큐(Queue)` 구조에 방문한 점을 넣어가며 탐색한다.<br/>
한 점에 연결된 모든 점의 탐색이 끝나면 그 점을 큐에서 제거한다.<br/>
그리고 큐의 다음 점을 꺼내서 그 점에 연결된 모든 점을 탐색한다.<br/>

    // Pseducode
    BFS(Start, Goal)
        push(Queue, Start)
        while(noEmpty(Queue))
            Node <- Pop(Queue)
            if (Node == Goal)
                return Node
            forall Child <- Expand(Node)
                push(Queue, Child)

> 유용 : 최소신장트리(Minimum Spanning Trees), 최단경로(Shortest Paths)<br/>
> 장점
- 무한히 깊거나 무한에 가까운 트리인 경우에 효과적(노드의 수가 적고 깊이가 얕은 해에 유리)
> 단점
- 목표 노드로 가는 경로들이 모두 같은 비용일 때 비효율
- 큐를 이용해 다음에 탐색할 노드를 저장하여 더 큰 저장공간이 필요

    예제
    - BaekJoon_5014.java[스타트링크]
---
### **2. Depth First Search(DFS)**<br/>
   
   현재 점에 연결된 점이 존재하면 계속 이동한다.<br/>
   방문한 점을 재귀호출을 이용해서 스택에 저장한다.<br/>
   재귀호출을 이용하면 할수록 깊은 점의 위치에 들어간다. (overflow 유의) <br/>>
   막히면 나아갈 곳이 있는 곳으로 돌아간다 -> `백트래킹`

   > 유용 : 사이클 탐지(Cycle Detection), 위상정렬(Topological Sorting)<br/>
   > 장점
   - 무한히 넓은 트리에 효과적
   - 저장공간의 필요성이 적음.
   
   > 단점
   - 답이 아닌 경로가 매우 깊다면, 그 경로에 깊이 빠질 수 있음.
   
    예제
    - BaekJoon_1012.java[유기농 배추]
    - BaekJoon_2583.java[영역 구하기]
---
### **3. 다익스트라 알고리즘**<br/>

한 노드에서 다른 모든 노드까지의 최단거리를 구하는 알고리즘이다. <br/>처음 고안되었던 알고리즘은 v^2의 시간복잡도를 가졌으나, 큐(힙트리)등을 이용한 더욱 개선된 알고리즘이 나오며, ElogV의 시간복잡도를 가지게 되었다. <br/>최단경로를 구하는 다른 알고리즘인 플로이드-워셜 알고리즘은 모든 노드쌍들에 대한 최단거리를 구하는 알고리즘인 반면, <br/>다익스트라 알고리즘은 하나의 노드에서부터 최단경로 밖에 구할 수 없으나, 시간이 더 단축되므로 상황에 맞는 알고리즘을 선택할 수 있다. <br/>다익스트라 알고리즘을 활용한 예시 알고리즘으로는 `루빅스 큐브`, `미로탐색` 등이 있다.

#### 3-1 다익스트라 알고리즘
다익스트라의 알고리즘은 아래와 같다.(P[A][B]는 A와 B의 거리라고 하자.)

1. 출발점으로부터 최단거리를 저장할 배열 d[v]를 만들고, 출발 노드에는 0을 출발점을 제외한 다른 노드들에는 매우 큰 값 INF를 채운다.(실무에서는 보통 d의 원소 타입에 대한 최대값으로 설정하는 편)
2. 현재 노드 A에 출발 노드를 저장한다.
3. A로부터 갈 수 있는 임의의 노드 B에 대해, d[A] + P[A][B](A와 B의 거리)와 d[B](현재까지 알려진 B까지의 최단거리)의 값을 비교한다.
4. 만약 d[A] + P[A][B]가 더 짧다면, d[B]의 값을 갱신시킨다.
5. 모든 이웃 노드 B에 대해 이 작업을 수행한다.
6. A의 상태를 `방문 완료`로 변환한다. (A를 더 이상 활용하지 않기 위해서)
7. `미방문`상태인 모든 노드들 중 출발점으로부터의 거리가 제일 짧은 노드 하나를 골라 그 노드를 A에 저장한다.
8. 도착 노드가 `방문 완료`상태가 되거나, 혹은 더 이상 미방문 상태의 노드를 선택할 수 없을 때까지 3~7의 과정을 반복한다.

이 작업을 마친 뒤, 도착 노드에 저장된 겂이 바로 A로부터의 최단 거리이다. <br/>만약 이 값이 INF라면, 중간에 길이 끊긴 것임을 의미한다. 만약 큐를 활용한다면 비용을 줄일 수 있다. <br/>최단거리를 갱신할 때 큐에도 <위치, 거리> 의 쌍을 넣어주고, 거리가 가장 짧은 노드를 큐를 이용해 고르면 된다. <br/>이 비용은 삽입(log N), 출력(log N)이므로 모든 노드 순회(N)보다 저렴하다.

#### 3-2. 의사코드
    function Dijkstra(Graph, Source):
        dist[source] <- 0                       // 출발지 - 출발지 거리는 0
        prev[source] <- undefined               // 출발지보다 이전의 경로는 없으므로 undefined
        
        create vertex set Q                     // 노드들의 집합 Q(방문하지 않는 노드) 생성

        for each vertex v in Graph:             // 모든 노드 초기화
            if v != source:                     // 출발지가 아닐 경우
                dist[v] <- INFINITY             // 출발지와의 거리를 모르므로 무한으로 설정
                prev[v] <- UNDEFINED            // 노드의 최적경로 추적 초기화
            add v to Q  // 방문하지 않은 노드들이므로 Q에 추가

        while Q is not empty:
            u <- vertex in Q with min dist[u]   // 첫번째 반복에서는 dist[source] = 0을 선택. (u = source)
            remove u from Q                     // 방문했으므로 u를 Q에서 제거

            for each neighboor v of u:          // u의 이웃노드들과의 거리 측정
                alt <- dist[u] + length(u, v)   // 출발지 노드부터 계산된 u노드까지의 거리 + v에서 u의 아웃노드까지의 거리

                if alt < dist[v]:               // v노드까지 계산한 거리가(alt) 이전에 v노드까지 계산된 거리보다 가까울 경우
                    dist[v] <- alt              // v에 기록된 소스부터 v까지의 최단거리를 방금 v노드까지 계산한 거리로 바꿈
                    prev[v] <- u                // 제일 가까운 노드는 지금 방문하고 있는 노드(u)로 바꿈

        return dist[], prev[]                   // 계산된 거리값과 목적지를 리턴

---
### **4. 플로이드-워셜 알고리즘**<br/>
플로이드-워셜(Floyd-Warshall Algorithm)은 그래프에서 가능한 모든 노드 쌍에 대해 최단 거리를 구하는 알고리즘이다.<br/>
시간복잡도는 V^3이다. 다익스트라 알고리즘과는 달리 모든 노드 쌍에 대해 최단 거리를 구하고, 음의 가중치를 가지는 그래프에서도 사용이 가능하다는 것이 특징이다.<br/>

플로이드-워셜 알고리즘은 임의의 노드 s에서 e까지 가는 데 걸리는 최단거리를 구하기 위해, s와 e 사이의 노드인 m에 대해 s와 m까지 가는데 걸리는 최단거리와 e와 m까지 가는 데 걸리는 최단거리를 이용한다.<br/>


#### 4-2. 의사코드
for문을 3번 중첩시키면 되기 때문에 구현에 있어 크게 어려운 부분은 없다. (단, for문에서 가운데 노드(m)가 제일 위에 있어야 한다.)
    
    void Floyd_Warshall() {
        for(m=1; m <= N; m++)
            for (s=1; s <= N; s++)
                for (e=1; e<=N; e++)
                    if (d[s][e] > d[s][m] + d[m][e]) d[s][e] = d[s][m] + d[m][e];
    }
---
### **5. Union-Find(Disjoint-set)**<br/>
Union-Find(or Disjoint Set)은 상호 배타적으로 이루어진 집합을 효율적으로 표현하기 위해 만들어진 자료 구조이다.  집합의 원소가 어떠한 집합에 속해있는지 판단하는 `Find 연산`과 자료 구조가 서로 다른 두 개의 집합을 병합하는 `Union 연산`을 지원한다.

#### 5-1. Find 연산
Find 연산이 수행되면, 재귀적으로 트리를 거슬러 올라가 최상위 노드의 값을 반환한다.
트리 형태로 구현된 Disjoint Set에서 최상위 노드는 각 집합과 1대 1대응되므로, Find 연산을 통해 각 집합을 알 수 있게 된다.
<br/>

> Find 연산의 최적화
>> 매번 트리를 거슬러 올라가는 것을 보완하기 위해, Find 연산에서 방문하는 각 노드마다 결과값을 반환하기전에 List에 해당 원소의 값을 결과값으로 저장한다. (Path compression)

#### 5-2. Union 연산

Union 연산이 수행되면, 먼저 Find 연산을 수행한 후 두 개의 최상위 노드의 부모를 다른 하나의 최상위 노드로 바꾸어 트리를 병합시킨다.

> Union 연산의 최적화
>> 최악의 상황인 트리의 편중을 해결하기위해, List를 하나 더 만들어서 트리의 대략적인 깊이를 저장한다. 그리하여 Union 연산을 수행할 때 rank(깊이)가 큰 트리에 rank가 작은 트리를 합치도록 변경하면 트리의 깊이를 줄이는 효과가 있다.

#### 5-3. 의사코드
`최적화 전`<br/>

    Function find(index):
        if list[index] == index:
            return index
        else:
            return find(list[index])
    
    Function union(a, b):
        roota = self.find(a)
        rootb = self.find(b)
        list[roota] = list[rootb]

`최적화 후`<br/>

    Funciton find(index):
        if list[index] == index:
            return index
        else:
            rank[index] = rank[index] + 1
            return list[index] = find(list[index])  // path Compression

    Function union(a, b):
        roota = self.find(a)
        rootb = self.find(b)
        if(rank[roota] > rank[rootb]) swap(roota, rootb)
        list[roota] = list[rootb]
---
### **6. 프림 알고리즘(Prim's algorithm)**
가중치가 있는 모든 꼭지점을 포함하면서 각 변의 비용의 합이 최소가 되는 부분 그래프인 트리, 즉 최소비용생성 그래프를 찾는 알고리즘이다. 변의 개수를 E, 꼭짓점의 개수를 V라고 하면 이 알고리즘은 이진 힙을 이용하여 처리하였을 때는 O(ElogV)의 시간복잡도를 가진다.
또는 그래프의 변의 개수가 꼭지점의 개수보다 훨씬 많다면 피보나치힙을 이용하면 훨씬 빠르게 계산할 수 있다. 이 방법은 O(E + VlogV)까지 떨어진다.
프림 알고리즘은 그래프를 유지하면서 완성시켜가는 성질을 가지고 있다.
또한 프림 알고리즘은 사이클이 존재하는지를 검사하는 부분이 없다.


#### 6-1. 작동순서
1. 그래프에서 하나의 꼭짓점을 선택한다.
2. 그래프의 모든 변이 들어있는 집합을 만든다.
3. 모든 꼭짓점이 그래프에 포함되어 있지 않은 동안 그래프에 연결된 변 가운데 두 꼭짓점을 연결하지 않는 가장 가중치가 작은 변을 그래프에 추가한다.

#### 6-2. 의사코드
    ~~~
    Prim(G, w, r)
        ReachSet = {0};
        UnReachSet = {1, 2, ....., N-1};
        SpanningTree = {};

        while(UnReachSet is not empty) 
            Find edge e = (x, y)
                1. x ∈ ReachSet
                2. y ∈ UnReachSet
                3. e has smallest cost
            SpanningTree = SpanningTree ∪ {e};

            ReachSet = ReachSet ∪ {y};
            UnReachSet = UnReachSet - {y};
    ~~~
---
### **7. 크루스컬 알고리즘(Kruskal's algorithm)**
프림알고리즘과 마찬가리로 최소 스패닝 트리를 찾는 알고리즘으로, 시간복잡도는 O(ElogV)이다.

#### 7-1. 작동순서
1. 그래프의 꼭짓점이 각각 하나의 나무가 되도록 하는 숲 F를 만든다.
2. 모든 변을 원소로 갖는 집합 S를 만든다.
3. 집합 S가 비어있지 않는 동안
		3-1. 가장 작은 가중치의 변을 S에서 하나 빼낸다.
		3-2. 그 변이 어떤 두 개의 나무를 연결한다면 두 나무를 연결하여 하나의 나무로 만든다.
		3-3. 그렇지 않다면 그 변은 버린다.
알고리즘이 종료돼었을 때 숲 F는 하나의 최소 스패닝 트리를 가지게 된다.

크루스컬 알고리즘은 자료구조의 영향을 덜 받으나, 그래프가 뺵뺵한 경우 프림 알고리즘이 성능이 더 좋은 경향을 보인다.
---
출처 <br/>
    - BFS / DFS 차이 : https://m.blog.naver.com/PostView.nhn?blogId=premiummina&logNo=220644200194&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F 
    <br/>
    - 그래프 : https://www.zerocho.com/category/Algorithm/post/584b9033580277001862f16c
    <br/>
    - 다익스트라 알고리즘 : https://namu.wiki/w/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98#fn-4
    <br/>
    - 플로이드-워셜 알고리즘 : https://namu.wiki/w/%ED%94%8C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%9B%8C%EC%85%9C%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
    <br/>
    - Union-Find 알고리즘 : https://namu.wiki/w/Union%20Find
    <br/>
    http://bowbowbow.tistory.com/26
