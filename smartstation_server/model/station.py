from .base import SerializableBase


class Station(SerializableBase):
    def __init__(self):
        self.id: str = ""
        self.name: str = ""
        self.description: str = ""
        self.type: str = "Both"
        self.lng: float = 0
        self.lat: float = 0
