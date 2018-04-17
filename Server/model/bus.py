from .base import SerializableBase

class Bus(SerializableBase):
    def __init__(self):
        self.licensePlate = ""
        self.lat = 0
        self.lng = 0