
import json
from model.route import Route
from model.station import Station
from model.stop import BusStop
from model.bus import Bus


routeNumbers = [30, 242, 331, 353]

stations = {}
routes = []

for rnumber in routeNumbers:

    filepath = "./data/json/{}.json".format(rnumber)

    file = open(filepath, "r", encoding="UTF-8")
    jsonContent = json.load(file)

    route = Route()
    route.number = rnumber

    for obj in jsonContent:
        stop = BusStop()

        id = obj["Makat"]

        if id in stations:
            station = stations[id]
        else:
            station = Station()
            station.id = id
            if obj["EnglishName"] is not "":
                station.name = obj["EnglishName"]
            else:
                station.name = obj["HebrewName"]

            station.description = obj["EnglishPlaceDescription"]
            station.lat = obj["Latitude"]
            station.lng = obj["Longitude"]
            stype = obj["EnglishBusStopType"]
            station.type = stype
            stations[id] = station

        stop.stationId = station.id
        stop.timeToNext = obj["TimeFromOrigin"]
        stop.distanceToNext = obj["Distance"]

        route.stops.append(stop)

    routes.append(route)

    file.close()

stationsFilePath = "./data/stations.json"
routesFilePath = "./data/routes.json"

with open(stationsFilePath, "w",encoding="UTF-8") as file:
    arr = []
    for key in stations:
        arr.append(stations[key].toJson())
    json.dump(arr, file)

with open(routesFilePath, "w",encoding="UTF-8") as file:
    json.dump([route.toJson() for route in routes], file)