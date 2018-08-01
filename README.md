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
> 장점 : 무한히 깊거나 무한에 가까운 트리인 경우에 효과적<br/>
> 단점 : 목표 노드로 가는 경로들이 모두 같은 비용일 때 비효율<br/>

    예제
    - BaekJoon_5014.java[스타트링크]
---
### **2. Depth First Search(DFS)**<br/>
   
   현재 점에 연결된 점이 존재하면 계속 이동한다.<br/>
   방문한 점을 재귀호출을 이용해서 스택에 저장한다.<br/>
   재귀호출을 이용하면 할수록 깊은 점의 위치에 들어간다. (overflow 유의) <br/>>
   막히면 나아갈 곳이 있는 곳으로 돌아간다 -> `백트래킹`

   > 유용 : 사이클 탐지(Cycle Detection), 위상정렬(Topological Sorting)<br/>
   > 장점 : 무한히 넓은 트리에 효과적<br/>
   > 단점 : 목표 노드가 없는 경로에 깊이 빠질 수 있다.

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

출처 <br/>
    - BFS / DFS 차이 : https://m.blog.naver.com/PostView.nhn?blogId=premiummina&logNo=220644200194&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F 
    <br/>
    - 그래프 : https://www.zerocho.com/category/Algorithm/post/584b9033580277001862f16c
    <br/>
    - 다익스트라 알고리즘 : https://namu.wiki/w/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98#fn-4