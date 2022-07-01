# ftc-easing

This repository is a proof-of-concept for non-blocking eased motor controls in
Java. It could be used in combination with an odometry library like Roadrunner.

This branch has the user enter percentages in the terminal and transitions to
those.

## Overview

A `BlockingQueue` is used to send messages to motors, each of which has its own
thread. The messages are received and processed quickly, barely blocking the
sender. Upon receiving a message, the motor begins
transitioning to the new power. If a transition is already in progress, it
begins a new one to ensure smoothness is maintained.

This example's implementation of a `Motor` prints a line of text to represent
power changes for easy visualization.

Some easing functions are available at [easings.net](https://easings.net).
easeInOutSine is used here.

An easing function that maps a range [0, 1] to [0, 1] can be made to transition
between two values using this formula:

![math](math.svg)

[Demonstration on Desmos](https://www.desmos.com/calculator/5xyatl4dun)

## Example

```text
    // First message is sent
Beginning transition to 50% power
[----------------------------------------------------------------] 0%
[----------------------------------------------------------------] 0%
[#---------------------------------------------------------------] 2%
[##--------------------------------------------------------------] 4%
[####------------------------------------------------------------] 7%
[#######---------------------------------------------------------] 11%
[##########------------------------------------------------------] 15%
[#############---------------------------------------------------] 20%
[###############-------------------------------------------------] 24%
    // Another is sent while transition is in progress
Beginning transition to 100% power
[###############-------------------------------------------------] 24%
[################------------------------------------------------] 25%
[#################-----------------------------------------------] 27%
[###################---------------------------------------------] 30%
[#######################-----------------------------------------] 36%
[##########################--------------------------------------] 41%
[##############################----------------------------------] 48%
[###################################-----------------------------] 55%
[#######################################-------------------------] 61%
[############################################--------------------] 68%
[################################################----------------] 75%
[###################################################-------------] 81%
[#######################################################---------] 86%
[##########################################################------] 91%
[############################################################----] 95%
[##############################################################--] 97%
[###############################################################-] 99%
[################################################################] 100%
```
