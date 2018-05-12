import networkx as nx
from typing import List

from .route import Route
from .station import Station
from .stop import BusStop

import matplotlib.pyplot as plt


def buildGraph(stations: List[Station], routes: List[Route]) -> nx.Graph:
    graph = nx.Graph()

    for station in stations:
        graph.add_node(station.id)

    for route in routes:
        lastStop = route.stops[0]
        for stop in route.stops[1:]:
            distance = stop.distanceFromOrigin - lastStop.distanceFromOrigin
            time = stop.timeFromOrigin - lastStop.timeFromOrigin
            weight = distance/100 * 2 + time/24 * 5
            graph.add_edge(lastStop.stationId, stop.stationId, weight=weight)
            lastStop = stop

    return graph


def drawGraph():
    from ..database import database_handler
    dbHandler = database_handler.DatabaseHandler()
    stations = dbHandler.getStations()
    graph = buildGraph(stations, dbHandler.getRoutes())
    pos = {station.id: (station.lat, station.lng) for station in stations}
    nx.draw(graph, pos=pos)
    plt.show()


def calcShortestPaths(stations: List[Station], routes: List[Route], sourceStation: Station, destStation: Station):
    graph = buildGraph(stations, routes)
    path = nx.dijkstra_path(graph, sourceStation, destStation)
    return path
