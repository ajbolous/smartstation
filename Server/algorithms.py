import networkx as nx
from .model.route import Route
from .model.station import Station
from typing import List

def buildGraph(stations:List[Station] , routes: List[Route], source:Station, destination:Station) -> nx.Graph:
    graph = nx.Graph()
    for station in stations:
        graph.add_node(station.id)

    return graph
        
def calcPaths(stations:List[Station], routes:List[Route], source:Station, destination:Station):
    graph = buildGraph(stations, routes, source, destination)
    