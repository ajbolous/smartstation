from .base import SerializableBase

class Station(SerializableBase):
    def __init__(self):
        self.id = ""
        self.name = ""
        self.description = ""
        self.type = "Both"
        self.lng = 0
        self.lat = 0
