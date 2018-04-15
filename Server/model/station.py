class Station:
    def __init__(self, makat, id, name, distance, arrivaltime, timefromorigin, lng, lat):
        self.makat = makat
        self.id = id
        self.name = name
        self.distance = distance
        self.arrivaltime = arrivaltime
        self.timefromorigin = timefromorigin
        self.lng = lng
        self.lat = lat

    def toJson(self):
        return {
            "Makat": self.makat,
            "Id": self.id,
            "Name": self.name,
            "Distance": self.distance,
            "ArrivalTime": self.arrivaltime,
            "TimeFromOrigin": self.timefromorigin,
            "Longitude": self.lng,
            "Latitude": self.lat
        }

def fromJson(jsonObj):
    return Station(jsonObj['Makat'], jsonObj['Id'], jsonObj['Name'], jsonObj['Distance'], jsonObj['ArrivalTime'], jsonObj['TimeFromOrigin'], jsonObj['Longitude'], jsonObj['Latitude'])
