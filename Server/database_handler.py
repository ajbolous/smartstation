import json
from model.station import fromJson, Station


class DatabaseHandler():
    def __init__(self):
        self.stations = self.getStations()

    def getStations(self):
        stationObjects = []
        file = open("/Users/lilian/smartstation/Server/data/stations.json", mode="r")
        stations = json.load(file)
        for station in stations:
            stationObjects.append(fromJson(station))
        return stationObjects
