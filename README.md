[coordinate_system]: https://raw.githubusercontent.com/mikeburke106/mines/master/docs/images/Coordinate_System.png "2D Coordinate System"

# mines

`mines` is an open-source project meant to help people create Minesweeper-like applications for various platforms.

The `mines` library provides APIs to generate new games with various configurations along with APIs to perform common operations, such as flagging a position, clearing a position, determining the number of adjacent mines and win/loss criteria.  The library is flexible, so if your version of the game has different rules, you should be able to inject those rules to the engine with minimal effort on your part.  If you need to inject functionality that the library is not flexible enough to handle, please file an issue for community discussion.

Additionally, the `mines` library will provide some default images available as part of the open-source license, so you don't even need additional artwork to begin your Minesweeper-like application!

## Language Support

`mines` was started in Java, but the plan is to expand to other popular and useful languages, such as JavaScript, Swift, C++, C#, GoLang, etc.  Check out the CONTRIBUTING.md page for information about starting work on a new platform or language.

# Basic Game Elements

The following section outlines the basic model and attributes used in the `mines` project.

## Game.java

`Game.java` is an interface which defines the API for a game object.  The game object allows you to define a timing strategy - generally, counting up in seconds - starting and stopping of the game clock, access to current game clock and timestamp for when the game was created.

`Game.java` also provides access to the underlying `Field` object.

## Field.java

`Field.java` is an interface which defines the API for the play field object.  The play field allows flagging or clearing of a position on the field, access to configuration and checking status of a position (if it is currently flagged or a mine).

A field's configuration defines the available positions on the field as well as the number of mines on the field.  The available positions are defined as a Position.Pool object.

## Position.java

`Position.java` is an interface which defines the API for a single position object on the play field.  Currently, positions are assumed to be 2-dimensional, so the position object is simply an X-Y coordinate.

### Coordinate System

The coordinate system used in the library is defined as the x-axis being the horizontal axis, beginning from 0 at the left-most side of the field and the y-axis being the vertical axis, beginning from 0 at the top-most side of the field.

![Image Not Loaded][coordinate_system]

### Position Pools

A `Position.Pool` object is a pool of all available positions on the Field.  A position pool is defined by a width and a height and provides Position objects based on a given X-Y coordinate.  For any given X-Y coordinate, the Position Pool must return the same object.

## GameControlStrategy.java

`GameControlStrategy.java` is an interface which defines the API for control of the game as well as a listener API for game update callbacks.

# Default Implementations

Basic models have been provided for the majority of these generic interfaces.  `BasicField.java`, for example, implements a basic field defined by a Field.Configuration object, a set of positions representing positions containing a mine and a set of positions representing positions containing a flag.

Similarly, `BasicGame.java` implements a basic game, defined by a field and a TimingStrategy for keeping game time.

Some basic JSON objects have also been provided to display how a game can be persistently saved and loaded using a simple JSON conversion.