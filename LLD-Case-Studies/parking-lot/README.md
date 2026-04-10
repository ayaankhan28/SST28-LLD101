# Parking Lot System - Low Level Design

## Overview
A comprehensive parking lot management system that handles vehicle parking, slot assignment, billing, and real-time status tracking across multiple floors.

## Features
- **Multi-floor Support**: Handles parking lots with multiple floors
- **Multiple Entry Gates**: Supports multiple entry gates on different floors
- **Smart Slot Assignment**: Assigns nearest available slot based on entry gate
- **Vehicle Type Support**: TWO_WHEELER, CAR, and BUS
- **Slot Type Support**: SMALL, MEDIUM, and LARGE slots
- **Flexible Pricing**: Configurable pricing policy per slot type
- **Overflow Handling**: Automatically assigns larger slots when requested type is full
- **Real-time Status**: Track available, occupied, and total slots by type

## Design Patterns Used

### 1. Strategy Pattern
- **SlotAssignmentStrategy**: Defines how slots are assigned to vehicles
  - `NearestSlotStrategy`: Assigns the nearest available slot from entry gate
- **PricingPolicy**: Defines how parking fees are calculated
  - `FlatRatePricingPolicy`: Simple per-hour rate based on slot type

### 2. Dependency Inversion Principle
- High-level modules (ParkingLot, BillingService) depend on abstractions (interfaces)
- Easy to add new strategies without modifying existing code

## Class Structure

### Core Entities
- **Vehicle**: Represents a vehicle with license plate and type
- **ParkingSlot**: Represents a parking slot with number, type, floor, and occupancy status
- **Gate**: Represents an entry/exit gate with number and floor location
- **ParkingTicket**: Generated when a vehicle enters, contains entry time and slot assignment
- **Bill**: Generated when a vehicle exits, contains parking duration and amount

### Enums
- **VehicleType**: TWO_WHEELER, CAR, BUS
- **SlotType**: SMALL, MEDIUM, LARGE

### Services
- **ParkingLot**: Main orchestrator for parking operations
- **BillingService**: Handles bill generation based on parking duration
- **VehicleSlotMapping**: Defines which slot types are compatible with each vehicle type

### Strategies
- **SlotAssignmentStrategy**: Interface for slot assignment algorithms
- **PricingPolicy**: Interface for pricing calculations

## Vehicle-Slot Compatibility

| Vehicle Type | Compatible Slot Types |
|-------------|----------------------|
| TWO_WHEELER | SMALL, MEDIUM, LARGE |
| CAR         | MEDIUM, LARGE        |
| BUS         | LARGE                |

## Pricing Example
- SMALL slot: Rs. 10/hour
- MEDIUM slot: Rs. 20/hour
- LARGE slot: Rs. 50/hour

## Key Operations

### 1. Park Vehicle
```java
ParkingTicket ticket = parkingLot.park(vehicle, entryTime, requestedSlotType, gateId);
```
- Finds nearest available compatible slot
- Marks slot as occupied
- Generates and returns parking ticket

### 2. Exit Vehicle
```java
Bill bill = parkingLot.exit(ticket, exitTime);
```
- Calculates parking duration
- Generates bill based on slot type and duration
- Frees the slot for next vehicle

### 3. Check Status
```java
Map<SlotType, long[]> status = parkingLot.status();
```
- Returns total, available, and occupied counts for each slot type

## Slot Assignment Algorithm (NearestSlotStrategy)

1. First tries to find the exact requested slot type
2. Calculates distance based on:
   - Floor difference from entry gate
   - Slot number (as tiebreaker)
3. If requested type is full, finds next compatible larger slot type
4. Returns the nearest available compatible slot

## Billing Logic

- Parking duration is calculated in minutes
- Rounded up to nearest hour (even 1 minute = 1 hour)
- Total amount = hours × rate per hour for slot type
- Billing is based on actual slot type assigned (not vehicle type)

## Example Usage

See `App.java` for a complete working example that demonstrates:
- Parking multiple vehicle types
- Status checking
- Vehicle exits and billing
- Overflow scenario (bike getting medium slot when small slots are full)

## Extensibility

The design is easily extensible:
- Add new vehicle types by updating `VehicleType` enum and `VehicleSlotMapping`
- Add new slot types by updating `SlotType` enum
- Implement new pricing strategies by implementing `PricingPolicy` interface
- Implement new slot assignment algorithms by implementing `SlotAssignmentStrategy` interface

## SOLID Principles Applied

1. **Single Responsibility**: Each class has one clear responsibility
2. **Open/Closed**: Open for extension (new strategies) but closed for modification
3. **Liskov Substitution**: Strategy implementations are interchangeable
4. **Interface Segregation**: Small, focused interfaces
5. **Dependency Inversion**: Depends on abstractions, not concrete implementations
