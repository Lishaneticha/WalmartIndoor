package com.lishan.walmartindoor.view

//
//data class Node(val id: String, val position: Offset)
//data class Edge(val from: String, val to: String, val weight: Float)
//
//fun dijkstra(
//    nodes: List<Node>,
//    edges: List<Edge>,
//    startId: String,
//    endId: String
//): List<Offset> {
//    val nodeMap = nodes.associateBy { it.id }
//    val distances = mutableMapOf<String, Float>().withDefault { Float.POSITIVE_INFINITY }
//    val previous = mutableMapOf<String, String?>()
//    val visited = mutableSetOf<String>()
//    val queue = mutableListOf(startId)
//
//    distances[startId] = 0f
//
//    while (queue.isNotEmpty()) {
//        val current = queue.minByOrNull { distances.getValue(it) } ?: break
//        queue.remove(current)
//        visited.add(current)
//
//        edges.filter { it.from == current && it.to !in visited }.forEach { edge ->
//            val alt = distances.getValue(current) + edge.weight
//            if (alt < distances.getValue(edge.to)) {
//                distances[edge.to] = alt
//                previous[edge.to] = current
//                if (edge.to !in queue) queue.add(edge.to)
//            }
//        }
//    }
//
//    val path = mutableListOf<Offset>()
//    var u: String? = endId
//    while (u != null) {
//        path.add(nodeMap[u]!!.position)
//        u = previous[u]
//    }
//    return path.reversed()
//}
//
//@Composable
//fun CustomMapScreen() {
//    var scale by remember { mutableStateOf(1f) }
//    var offset by remember { mutableStateOf(Offset.Zero) }
//
//    /*
//         X ----
//        y
//        |
//        |
//    */
//
//    val nodes = listOf(
//        Node("A", Offset(100f, 100f)),
//        Node("B", Offset(300f, 100f)),
//        Node("C", Offset(300f, 300f)),
//        Node("D", Offset(100f, 300f)),
//        Node("E", Offset(600f, 100f)),
//    )
//
//    val edges = listOf(
//        Edge("A", "B", 200f),
//        Edge("B", "C", 200f),
//        Edge("C", "D", 200f),
//        Edge("D", "A", 200f),
//        Edge("B", "E", 200f),
//    )
//
//    val userNodeId = "D"
//    val targetNodeId = "E"
//
//    val path = remember { dijkstra(nodes, edges, userNodeId, targetNodeId) }
//
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .pointerInput(Unit) {
//            detectTransformGestures { _, pan, zoom, _ ->
//                scale = (scale * zoom).coerceIn(0.5f, 3f)
//                offset += pan
//            }
//        }) {
//
//        Canvas(modifier = Modifier.fillMaxSize()) {
//            scale(scale, pivot = Offset.Zero) {
//                translate(offset.x, offset.y) {
//                    // Draw edges
//                    edges.forEach { edge ->
//                        val from = nodes.find { it.id == edge.from }!!.position
//                        val to = nodes.find { it.id == edge.to }!!.position
//                        drawLine(
//                            color = Color.LightGray,
//                            start = from,
//                            end = to,
//                            strokeWidth = 4f
//                        )
//                    }
//
//                    // Draw nodes
//                    nodes.forEach { node ->
//                        drawCircle(
//                            color = if (node.id == userNodeId) Color.Green else if (node.id == targetNodeId) Color.Red else Color.DarkGray,
//                            radius = 15f,
//                            center = node.position
//                        )
//                    }
//
//                    // Draw path
//                    path.windowed(2).forEach { (a, b) ->
//                        drawLine(
//                            color = Color.Blue,
//                            start = a,
//                            end = b,
//                            strokeWidth = 6f
//                        )
//                    }
//                }
//            }
//        }
//    }
//}