# Graph Component (OSU CSE)

## Overview
This project implements a custom Graph component following the OSU CSE
software sequence discipline. The component models a directed graph using
an adjacency-list representation.

## Features
- Add/remove vertices
- Add/remove directed edges
- Query connectivity
- Compute in-degree and out-degree
- Perform reachability (BFS traversal)

## Design
The component follows the OSU layered architecture:

- `GraphKernel` – minimal core operations
- `Graph` – enhanced interface
- `GraphSecondary` – implementation of secondary methods
- `Graph1L` – kernel implementation (adjacency list)

## Representation
The graph is represented as:
- `Map<V, Set<V>>` mapping each vertex to its outgoing neighbors
- An integer tracking total edges

## Testing
JUnit tests are provided for:
- Kernel methods (`Graph1LTest`)
- Secondary methods (`GraphSecondaryTest`)

Tests follow OSU discipline:
- Verify return values
- Verify object state is unchanged for non-mutating methods

## Use Cases
1. Network reachability analysis (cybersecurity)
2. Course prerequisite planning system

## How to Run
Compile and run demo files in `src/`:

- `NetworkReachabilityDemo`
- `CoursePlanner`

## Author
Rand Mustafa