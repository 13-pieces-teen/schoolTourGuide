package com.example.schooltourguide.algorithm;

import com.example.schooltourguide.DBHelper.MapDBHelper;
import com.example.schooltourguide.entity.Sight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PathDfs {

    private MapDBHelper mapDBHelper;
    private List<Sight> sightList = new ArrayList<Sight>();

    // 是否是无向图
    private boolean noDirect = false;
    // 定义一个图
    private int[][] Graph = null;
    // 边
    private List<List<String>> edges = new ArrayList<>();
    // 顶点
    private List<String> vertex = new ArrayList<>();
    // visit数组，用于在dfs中记录访问过的顶点信息。
    private int[] visit = null;
    // 存储每条可能的路径
    private List<String> path = new ArrayList<>();
    // 用于存储所有路径的集合
    private List<String> ans = new ArrayList<>();
    // 起点和终点
    private int start;
    private int end;

    public PathDfs() {
        super();
    }

    public PathDfs(boolean noDirect) {
        super();
        this.noDirect = noDirect;
    }

    public boolean isNoDirect() {
        return noDirect;
    }

    public void setNoDirect(boolean noDirect) {
        this.noDirect = noDirect;
    }

    /**
     * 寻找两点之间所有路线
     * @param start 起点
     * @param end 终点
     * @return
     */
    public List<String> findAllPath(String start, String end) {
        //通过边获取顶点
        initVertex();
        //构建邻接矩阵图
        buildGraph();
        //顶点转换下标
        visit = new int[vertex.size()];
        this.start = vertex.indexOf(start);
        this.end = vertex.indexOf(end);
        dfs(this.start);

        return this.ans;
    }

    /**
     *
     */
    private void dfs(int u) {
        visit[u] = 1;
        path.add(vertex.get(u));
        if (u == end) {
            ans.add(path.toString());
        } else {
            for (int i = 0; i < vertex.size(); i++) {
                if (visit[i] == 0 && i != u && Graph[u][i] == 1) {
                    dfs(i);
                }
            }
        }
        path.remove(path.size() - 1);
        visit[u] = 0;
    }

    /**
     * 获取所有路线
     * @return
     */
    public List<String> getAllPath() {
        return this.ans;
    }

    /**
     * 添加边
     * @param v1 边的起点
     * @param v2 边的终点
     */
    public void addEdge(String v1, String v2) {
        edges.add(Arrays.asList(v1, v2));
    }

    /**
     * 初始化顶点
     */
    private void initVertex() {
        Set<String> set = new HashSet<String>();
        for (List<String> edge : edges) {
            set.add(edge.get(0));
            set.add(edge.get(1));
        }
        for (String string : set) {
            vertex.add(string);
        }
    }

    /**
     * 构建邻接矩阵图
     */
    private void buildGraph() {
        Graph = new int[vertex.size()][vertex.size()];

        for (int i = 0; i < edges.size(); i++) {
            List<String> edge = edges.get(i);
            String ss = edge.get(0);
            String ee = edge.get(1);
            Graph[vertex.indexOf(ss)][vertex.indexOf(ee)] = 1;
            if (noDirect) {
                Graph[vertex.indexOf(ee)][vertex.indexOf(ss)] = 1;
            }
        }
    }


}
