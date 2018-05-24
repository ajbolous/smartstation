from .base import SerializableBase


class User(SerializableBase):
    def __init__(self):
        self.email: str = ''
        self.password: str = ''
        self.userType: str = 'Driver'
        self.firstName: str = ''
        self.lastName: str = ''
        self.age: int = ''
        self.phone: str = ''
        self.company: str = ''
