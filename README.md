archeng
=======

A Java implementation of The Archmage Engine (OGL engine created for the "13th Age" tabletop RPG)

Web: [Site](http://archeng.org)

FAQ
===

What can I actually do with this library?
-----------------------------------------

Currently, nothing.  The project is just starting. Release notes will be added as functionality is developed.

What will I eventually be able to do with this library?
-----------------------------------------

At a high level, this library plans to provide a representation of the game-state of a game run under the Archmage Engine.  Users of the library will be able to initialize the engine by providing custom rules such as the lists of feats, icons, and classes to be used in the game. The library will provide serialization of game-state for network-transfer and persistence ("saves").  Mutation of state will be validated according to the mechanics of the Archmage Engine.  Potential applications using this library could be a character-creation/editing tool, a prototyping environment to test new character-classes, an app to assist a GM and players manage a battle, or a reference-book application to search for feats and powers.

Why Java?
---------

There are many wonderful languages this could be implemented in. Java happens to work well for inclusion in both Android and webapp projects. The serialization schema however uses JSON for interoperability.

Can I help?
-----------

Sure!  Reach out on github or [email](mailto:seventhgriffin@outlook.com).

What's the license?
-------------------

The represented game mechanic, the Archmage Engine, is released under the Open Game License (OGL). This implementation is released under the BSD license. In summary, you are free to modify and (re)distribute this project in both source and binary format, so long as the included licenses remain, and are adhered to.
