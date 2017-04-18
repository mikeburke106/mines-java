[coordinate_system]: https://github.com/mikeburke106/mines/tree/master/mines-java/docs/images/Coordinate_System.png "2D Coordinate System"

# mines

`mines` is an open-source project meant to help people create Minesweeper-like applications for various platforms.

The `mines` library provides APIs to generate new games with various configurations along with APIs to perform common operations, such as flagging a position, clearing a position, determining the number of adjacent mines and win/loss criteria.  The library is flexible, so if your version of the game has different rules, you should be able to inject those rules to the engine with minimal effort on your part.  If you need to inject functionality that the library is not flexible enough to handle, please file an issue for community discussion.

Additionally, the `mines` library will provide some default images available as part of the open-source license, so you don't even need additional artwork to begin your Minesweeper-like application!

## Language Support

`mines` was started in Java, but the plan is to expand to other popular and useful languages, such as JavaScript, Swift, C++, C#, GoLang, etc.  Check out the CONTRIBUTING.md page for information about starting work on a new platform or language.

## Coordinate System

The coordinate system used in the library is defined as the x-axis being the horizontal axis, beginning from 0 at the left-most side of the field and the y-axis being the vertical axis, beginning from 0 at the top-most side of the field.

![Image Not Loaded][coordinate_system]
