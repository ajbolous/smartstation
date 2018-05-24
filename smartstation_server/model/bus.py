from .base import SerializableBase

class Bus(SerializableBase):
    def __init__(self):
        self.isActive:bool = False
        self.licensePlate: str = ''
        self.driverId: str = ''
        self.routeId: str = ''
        self.lat: float = 0
        self.lng: float = 0
        self.company: str = ''