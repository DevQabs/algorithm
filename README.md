# 알고리즘


## Graph
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
   재귀호출을 이용하면 할수록 깊은 점의 위치에 들어간다. (overflow 유의)
   막히면 나아갈 곳이 있는 곳으로 돌아간다 -> `백트래킹`

   > 유용 : 사이클 탐지(Cycle Detection), 위상정렬(Topological Sorting)<br/>
   > 장점 : 무한히 넓은 트리에 효과적<br/>
   > 단점 : 목표 노드가 없는 경로에 깊이 빠질 수 있다.

    예제
    - BaekJoon_1012.java[유기농 배추]
    - BaekJoon_2583.java[영역 구하기]
---

출처 <br/>
    - BFS / DFS 차이 : https://m.blog.naver.com/PostView.nhn?blogId=premiummina&logNo=220644200194&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F 
    <br/>
    - 그래프 : https://www.zerocho.com/category/Algorithm/post/584b9033580277001862f16c