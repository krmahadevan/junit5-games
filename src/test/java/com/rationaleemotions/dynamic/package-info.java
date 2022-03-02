/**
 * Dynamic Tests are not aware of life-cycle events and so they can't leverage call backs.
 * This means that you cannot define a call back which will do something before/after every test
 * if you build it as a dynamic test.
 *
 * Refer to this stackoverflow post for more details: https://stackoverflow.com/a/44114477
 */
package com.rationaleemotions.dynamic;