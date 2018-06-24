# VehicleAssemblyLine
The project represents assembly line for constructing vehicles (car and suv).
There are several commands that can be entered by the user in order to operate with the programme:
1. "create" - creates a vehicle of type "car" or "suv".
   There is a specific syntax when creating a vehicle and it must be followed strictly. Otherwise, the creation of the vehicle will be rejected
   (information about the encountered problem will be printed out). If syntax is fine - new vehicle is created and saved in persistent memory (vehicles.ser)
   and VIN is generated automatically for the vehicle.
   Possible syntax examples:
   create car model=A4 engine=D-6L-euro4 transmission=Auto-4
   create car model=A2 engine=B-986hp-euro3
   create suv model=Q5 engine=B
   create suv model=Q6 engine=D-196hp-euro5 transmission=Manual-6
   Invalid create commands:
   create car model=A1 engine=engine=E-718hp-euro6
   create suv model=A3 engine=D // suv models are from Q1 to Q8
   create car model=Q5 engine=B // car models are from A1 to A8
2. "disassemble" - disassembles vehicles by VIN.
3. "read" - reads create commands from a file on the computer.
4. "print" - print vin prints specified vehicle's information by the specified VIN
             print all prints all assembeled vehicles
   
   
   
