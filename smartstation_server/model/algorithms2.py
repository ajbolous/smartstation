class Edge():
    def __init__(self, source, destination, edgeType, distance, waitTime):
        self.source= source
        self.destination= destination
        self.edgeType = edgeType
        self.distance = distance
        self.waitTime = waitTime

class Vertex():
    def __init__(self, id):
        self.id = id
        self.neighbors = {}

    def addNeighbor(self, node, edgeType="bus", distance=1, waitTime=0):
        self.neighbors[node.id] = Edge(self, node, edgeType, distance, waitTime)
    
    def getNeighbor(self, id):
        return self.neighbors[id]

    def __getitem__(self, id):
        return self.getNeighbor(id)

class Graph():
    def __init__(self):
        self.nodes = {}
        self.edges = {}

    def addNode(self, stationId):
        node = Vertex(stationId)
        self.nodes[node.id] = node        

    def addEdge(self, uNode, vNode, routeId, edgeType="bus", distance=1, waitTime=1):
        if uNode not in self.nodes:
            self.addNode(uNode)

        if vNode not in self.nodes:
            self.addNode(vNode)

        uNode.addNeighbor(vNode)

