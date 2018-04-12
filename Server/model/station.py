class Station:
    def __init__(self, id, name, lat, lng):
        self.id = id
        self.name = name
        self.lat = lat
        self.lng = lng

    def toJson(self):
        return {
            "id": self.id,
            "name": self.name,
            "lat": self.lat,
            "lng": self.lng
        }

def fromJson(jsonObj):
    return Station(jsonObj['id'], jsonObj['name'], jsonObj['lat'], jsonObj['lng'])
