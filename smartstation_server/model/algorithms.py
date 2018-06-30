import networkx as nx
from typing import List

from .route import Route
from .station import Station
from .stop import BusStop

import matplotlib.pyplot as plt
from haversine import haversine

def buildGraph(stations: List[Station], routes: List[Route], start: Station ) -> nx.Graph:
    graph = nx.DiGraph()

    for route in routes:
        lastStop = route.stops[0]
        for stop in route.stops[1:]:
            distance = stop.distanceFromOrigin - lastStop.distanceFromOrigin
            time = stop.timeFromOrigin - lastStop.timeFromOrigin
            weight = distance/100 * 2 + time/24 * 5
            graph.add_edge(lastStop.stationId, stop.stationId, weight=weight)
            lastStop = stop

    for s1 in stations:
        for s2 in stations:
            if s1 == s2:
                continue
            distance = haversine((s1.lat, s1.lng), (s2.lat, s2.lng))
            if distance < 0.1:
                graph.add_edge(s1.id, s2.id, weight=(distance/100*2 + 5))
                graph.add_edge(s2.id, s1.id, weight=(distance/100*2 + 5))
            
    return graph


def drawGraph():
    from ..database import database
    graph = buildGraph(database.stations.all(), database.routes.all(), None)
    pos = {station.id: (station.lat, station.lng)
           for station in database.stations.all()}
           
    nx.draw_networkx(graph, pos=pos,arrows=True)
    plt.show()


def calcShortestPaths(stations: List[Station], routes: List[Route], sourceStation: Station, destStation: Station) -> List[int]:
    graph = buildGraph(stations, routes, None)
    path = nx.shortest_simple_paths(graph, sourceStation, destStation)
    return path
