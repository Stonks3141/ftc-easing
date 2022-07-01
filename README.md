# ftc-easing

This repository is a proof-of-concept for non-blocking eased motor controls in
Java. It could be used in combination with an odometry library like Roadrunner.

## Overview

A `BlockingQueue` is used to send messages to motors, each of which has its own
thread. The messages are received and processed quickly, barely blocking the
sender. Upon receiving a message, the motor begins
transitioning to the new power. If a transition is already in progress, it
begins a new one to ensure smoothness is maintained.

This example's implementation of a `Motor` prints a line of text to represent
power changes for easy visualization.

Some easing functions are available at [easings.net](https://easings.net).

## Example

```text
    // First message is sent
Beginning transition to 50% power


#
##
#####
#######
#########
############
##############
#################
    // Another is sent while transition is in progress
Beginning transition to 100% power
#################
#################
##################
####################
######################
########################
###########################
###############################
##################################
######################################
##########################################
##############################################
#################################################
#####################################################
########################################################
###########################################################
#############################################################
##############################################################
###############################################################
################################################################
################################################################
```
