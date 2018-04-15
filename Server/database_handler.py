import json
from model.station import fromJson, Station


class DatabaseHandler():
    def __init__(self):
        self.stations = self.getStations()

    def getStations(self):
        stationObjects = []
        file = open("/SS-Git/smartstation/Server/data/30.json", mode="r", encoding="utf8")
        stations = json.load(file)
        for station in stations:
            stationObjects.append(fromJson(station))
        return stationObjects
