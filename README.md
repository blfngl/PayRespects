# PayRespects
A Minecraft server plugin for paying respects.

### User Commands
 **/f** - Allows a player to pay respects to the general audience. May be used at any time, or during the ***respect window*** to pay the deceased.
 ```
 Notch: /f
 Notch paid their respects.
 ```
 **/f [player name]** - Allows a player to pay respects to a specific person. Doing so after the person has died will pay them an amount determined by server admins.
 ```
 Jeb_: /f Notch
 Jeb_ paid their respects to Notch.
 ```
 **/x** - Press x to doubt.
 ```
 Cole Phelps: /x
 Cole Phelps doubts.
 ```
 **/fhelp** - Displays some information about the plugin.

### Admin Commands
**/fdebug** - Toggles debug mode. Various commands will give debug messages/feedback to users.

**/fset** - Allows the user to change three settings: ***respect window***, ***payment amount*** and ***death message***.

1. ***Respect window*** - The amount of time users are given to pay their respects to the deceased.
```
blfngl: /fset window 15.
Setting respect window to 15.
```
2. ***Payment amount*** - The amount of money given to a deceased player per respect paid during the ***respect window***.
```
blfngl: /fset payment 3
Setting respect payment to 3.
```
3. ***Death message*** - The message broadcast to the server following a player's death.
```
blfngl: /fset death_msg Press F to pay respects.
Changed death message to: Press F to pay respects.
```
**/ftoggleheader** - Toggles the plugin chat header.
```
blfngl has died.
[PayRespects] Press F to pay respects.
```
compared to
```
blfngl has died.
Press F to pay respects.
```
**/xtoggle** - Toggles "Doubt Only" mode. "Doubt Only" makes it so that /x will only show "Doubt".

 ### Permissions
 **payrespects.\*** grants access to all PayRespects commands.
 
 **payrespects.user** grants access to all user commands.
