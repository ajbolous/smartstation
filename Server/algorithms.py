
calcPath( src, dst , stations, routes)

    1. get all relevant routes
    2. graph = from releavt routes construct graph (stations) edge(w : time/distance)
    3. path = find djikstra (src, dst, graph)
    4. return path

