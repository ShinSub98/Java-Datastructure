package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** <p>인접행렬 그래프</p>
 * 정점에는 0 혹은 자연수만 삽입할 수 있다.<br>
 * 간선의 유무를 나타내는 2차원 배열인 edge는 0일 경우 간선이 없음을, 1일 경우 간선이 존재함을 의미한다.
 */
public class AdjMatGraph {
    private int size = 0; // 그래프의 현재 정점 개수
    private int max_size; // 그래프의 최대 정점 개수
    private int[] vertex; // 정점의 배열
    private int[][] edge; // 간선의 배열

    public AdjMatGraph(int max_size) {
        this.vertex = new int[max_size];
        this.edge = new int[max_size][max_size];
        this.max_size = max_size;

        // 정점의 -1은 null로 간주
        for (int i = 0; i < max_size; i++) {
            vertex[i] = -1;
        }
    }


    /**
     * <p>현재 그래프 내 모든 정점 출력</p>
     */
    public void print_vertices() {
        System.out.printf("%nprint vertices");
        System.out.printf("%nsize = %d%n", size);
        System.out.println(Arrays.toString(vertex));
        System.out.println("");
    }


    /**
     * <p>현재 그래프 내 모든 간선 출력</p>
     */
    public void print_edges() {
        System.out.printf("%nprint edges%n");
        for (int[] i : edge) {
            System.out.println(Arrays.toString(i));
        }
        System.out.println("");
    }


    /**
     * 정점 삽입 메소드
     * @param val 삽입할 key
     */
    public void insert(int val) {
        // 음수는 삽입 불가
        if (val < 0) {
            System.out.println("Can not insert negative number");
            return;
        }

        // 그래프의 정점 개수가 max_size에 도달하면 삽입 불가
        if (size == max_size) {
            System.out.println("Graph is full");
            return;
        }

        int index = check_vertex(val);
        if (index != -1) {
            System.out.printf("%d already exists%n", val);
        }

        // vertex 배열에서 빈 자리를 찾아 val 삽입
        for (int i = 0; i < max_size; i++) {
            if (vertex[i] == -1) {
                vertex[i] = val;
                size += 1;
                System.out.printf("Inserted successfully: %d%n", val);
                break;
            }
        }
    }


    /**
     * <p>정점을 연결하여 간선을 생성하는 메소드</p>
     * @param x 연결할 정점1
     * @param y 연결할 정점2
     */
    public void link(int x, int y) {
        // 정점이 존재하지 않거나
        // 입력값이 유효하지 않을 때 메소드 종료
        int[] index = check_vertices(x, y);
        if (index == new int[]{-1, -1}) {
            return;
        }

        // 이미 간선이 존재하는 경우 메시지 출력
        if (edge[index[0]][index[1]] == 1 | edge[index[1]][index[0]] == 1) {
            System.out.println("Edge already exists");
            return;
        }

        // 조건을 만족하고 간선을 생성하는 경우
        edge[index[0]][index[1]] = 1;
        edge[index[1]][index[0]] = 1;
        System.out.printf("Linked successfully: %d - %d%n", x, y);
    }


    /**
     * <p>정점 간 연결을 끊어 간선을 삭제하는 메소드</p>
     * @param x 연결을 끊을 정점1
     * @param y 연결을 끊을 정점2
     */
    public void unlink(int x, int y) {
        // 정점이 존재하지 않거나
        // 입력값이 유효하지 않을 때 메소드 종료
        int[] index = check_vertices(x, y);
        if (index == new int[]{-1, -1}) {
            return;
        }

        // 원래 간선이 없는 경우 메시지 출력
        if (edge[index[0]][index[1]] == 0 | edge[index[1]][index[0]] == 0) {
            System.out.println("Vertices not linked");
            return;
        }

        // 조건을 만족하고 간선을 삭제하는 경우
        edge[index[0]][index[1]] = 0;
        edge[index[1]][index[0]] = 0;
        System.out.printf("Unlinked successfully: %d -/- %d%n", x, y);
    }

    /**
     * 정점 삭제 메소드
     * @param val 삭제할 정점의 Key
     */
    public void delete(int val) {
        // 정점이 존재하지 않을 경우
        if (size == 0) {
            System.out.println("There is no vertex in graph");
            return;
        }

        // 음수인 정점은 존재하지 않음
        if (val < 0) {
            System.out.println("Negative number does not exist in graph");
            return;
        }

        int index = check_vertex(val);

        // 정점이 존재할 경우
        if (index != -1) {
            // 해당 정점과 연결된 모든 간선 삭제
            for (int i = 0; i < max_size; i++) {
                if (edge[index][i] == 1) {
                    edge[index][i] = 0;
                    edge[i][index] = 0;
                }
            }

            // 정점 삭제
            vertex[index] = -1;
            size -= 1;
            System.out.printf("Deleted successfully: %d%n", val);
        }
    }

    /**
     * <p>특정 정점과 연결된 다른 정점들을 출력</p>
     * @param val 기준이 되는 정점
     */
    public void linked_vertices(int val) {
        if (val < 0) {
            System.out.println("Negative number does not exist in graph");
            return;
        }

        int index = check_vertex(val);
        if (index == -1) {
            System.out.println("No such vertex");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max_size; i++) {
            if (edge[index][i] == 1) {
                sb.append(String.valueOf(vertex[i]));
                sb.append(" ");
            }
        }

        String res = sb.toString();
        if (res == "") {
            System.out.println("The vertex is not linked to any vertices");
            return;
        }
        System.out.println(res);
    }


    /**
     * BFS로 두 정점 간 거리 확인 (무방향, 무가중 그래프일 경우 최단거리)
     * @param x 출발 정점
     * @param y 도착 정점
     */
    public void getDistance(int x, int y) {
        // x와 y가 같을 경우
        if (x == y) {
            System.out.println("same inputs");
            return;
        }

        // x나 y가 존재하지 않을 경우
        int[] index = check_vertices(x, y);
        if (index == new int[]{-1, -1}) {
            System.out.println("No such vertex");
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[max_size];

        queue.add(index[0]);
        visited[index[0]] = true;
        int distance = 0;
        int put = 1;
        int cnt = 0;
        boolean flag = false;

        while (!queue.isEmpty()) {
            cnt = put;
            put = 0;
            for (int i = 0; i < cnt; i++) {
                int v = queue.poll();
                if (vertex[v] == y) {
                    System.out.printf("distance between %d and %d : %d", x, y, distance);
                    flag = true;
                    break;
                }
                for (int k = 0; k < max_size; k++) {
                    if (edge[v][k] == 1 && visited[k] == false) {
                        queue.add(k);
                        visited[k] = true;
                        put++;
                    }
                }
            }
            if (flag) {break;}
            distance++;
        }
    }


    /**
     * BFS를 재귀로 구현한 바보짓
     * @param target 도착 정점
     * @param cnt 전 level에서 큐에 삽입된 정점의 개수
     * @param find visited 배열과 같음. 출발 정점은 true로 시작.
     * @param queue 다음 방문할 정점을 출력하는 큐. 출발 정점을 넣고 시작.
     * @param distance 현재까지 이동한 거리.
     * @return 출발 정점부터 도착 정점까지의 거리
     */
    private int BFS_recursion(int target, int cnt, boolean[] find, Queue<Integer> queue, int distance) {
        int put = 0;
        for (int i = 0; i < cnt; i++) {
            int index = queue.poll();
            if (vertex[index] == target) { return distance; }
            for (int k = 0; k < max_size; k++) {
                if (edge[index][k] == 1 && find[k] == false) {
                    queue.add(k);
                    find[k] = true;
                    put++;
                }
            }
        }
        return BFS_recursion(target, put, find, queue, distance+1);
    }


    /**
     * BFS를 통해 그래프의 모든 정점을 출력
     * @param val BFS 탐색을 시작할 정점의 Key
     */
    public void BFS(int val) {
        int idx = -1;
        for (int i = 0; i < max_size; i++) {
            if (vertex[i] == val) {
                idx = i;
                break;
            }
        }

        // val을 key로 가지는 정점이 존재하지 않을 경우 메소드 종료
        if (idx == -1) {
            System.out.println("There is no such vertext");
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[max_size];


    }


    /**
     * 특정 정점이 존재하는지 확인하는 메소드
     * @param val 존재하는지 확인할 정점의 key
     * @return 존재할 경우: 정점의 인덱스를 반환<br>존재하지 않을 경우: -1을 반환
     */
    private int check_vertex(int val) {
        for (int i = 0; i < max_size; i++) {
            // 정점이 존재할 경우 해당 정점의 인덱스 반환
            if (vertex[i] == val) {
                return i;
            }
        }

        // 정점이 존재하지 않을 경우 -1 반환
        return -1;
    }


    /**
     * 두 정점이 존재하는지 한 번에 유무를 확인하는 메소드
     * @param x 확인할 정점 1
     * @param y 확인할 정점 2
     * @return 둘 다 존재할 경우: {x의 인덱스, y의 인덱스}를 반환<br>하나라도 존재하지 않을 경우: {-1, -1}을 반환
     */
    private int[] check_vertices(int x, int y) {
        int[] loc = {-1, -1};

        // 음수인 정점은 존재하지 않음.
        if (x < 0 | y < 0) {
            System.out.println("Invalid value");
            return new int[]{-1, -1};
        }

        for (int i = 0; i < max_size; i++) {
            if (vertex[i] == x) {
                // vertex 배열에서의 x의 인덱스
                loc[0] = i;
            } else if (vertex[i] == y) {
                // vertex 배열에서의 y의 인덱스
                loc[1] = i;
            }
        }

        // 값이 x, y인 두 정점이 존재할 경우
        if (loc[0] != -1 && loc[1] != -1) {
            return loc;
        }

        // 값이 x, y인 정점이 하나라도 존재하지 않는 경우
        System.out.println("Vertex not found");
        return new int[]{-1, -1};
    }

}
