from .base import SerializableBase


class User(SerializableBase):
    def __init__(self):
        self.userid: str = ""
        self.password: str = ""
        self.firstName: str = ""
        self.lastName: str = ""
        self.age: int = ""
        self.phone: str = ""
