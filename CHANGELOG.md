# CHANGELOG

All notable changes to this project will be documented in this file.

Date Format: YYYY-MM-DD

## Types of Changes

Added: for new features.

Changed: for changes in existing functionality.

Deprecated: for soon-to-be removed features.

Removed: for now removed features.

Fixed: for any bug fixes.

Security: in case of vulnerabilities.

## [2026.04.24]

### Added

- Designed test suite for Graph component
- Added Graph1LTest and GraphSecondaryTest
- Created two use cases:
  - NetworkReachabilityDemo
  - CoursePlanner

### Updated

- Improved documentation and project structure
- Added README with usage and design overview

## [2026.04.01]

### Added

- Designed abstract class for Graph component
- Implemented all secondary Graph methods in GraphSecondary
- Implemented toString, equals, and hashCode using kernel methods only

### Updated

- Refined GraphKernel to expose vertices and neighbors so secondary methods could be implemented using kernel methods alone

## [2026-03-10]

### Added

- Designed kernel and enhanced interfaces for Graph component
- Added a hierarchy diagram showing the relationship between Standard, GraphKernel, and Graph

### Updated

- Refined the Graph design to use a minimal kernel and moved graph-analysis operations into the enhanced interface

## [2026-02-24]

### Added

- Designed a proof of concept for Graph component (single-file MVP with reachability demo)

## [2026-02-03]

### Added

- Designed a `Graph` component
- Designed a `GraphAssembleDisassemble` component
- Designed a `GraphWalk` component

- Designed abstract class for Graph component
- Implemented all secondary Graph methods in GraphSecondary
- Implemented toString, equals, and hashCode using kernel methods only

### Updated

- Refined GraphKernel to expose vertices and neighbors so secondary methods could be implemented using kernel methods alone

## [2026.04.15]

### Added

- Designed kernel implementation for Graph component
- Implemented Graph1L using an adjacency-list representation
- Added convention and correspondence for Graph1L

### Updated

- Refined the Graph representation to store vertices in a map from each vertex to its outgoing neighbor set
- Added explicit edge count tracking to make size efficient
