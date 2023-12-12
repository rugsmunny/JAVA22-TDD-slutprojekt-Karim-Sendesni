# JAVA22-TDD-slutprojekt-karim-sendesni
| School assignment (final project) | TDD | JUnit5 | Mocking |

# Introduction:
The task centered around applying Test Driven Development (TDD) principles to create and test a "producer-consumer" program. My contribution to the project lies in the creation of comprehensive tests, the development of two mock classes - MockProducer and MockConsumer, and the implementation of two helper classes - BufferHelper and ItemHelper.

# Classes and Interfaces:
The core components of the project include the interfaces for the producer and consumer, where only the interfaces are accessible, along with the classes Item and Buffer. This design strategy allowed a clear separation of responsibilities for the producer and consumer, while protecting the main classes behind testable interfaces.

# Test Cases and Requirements:
The various test cases cover a wide range of scenarios, including Boundary testing, handling Null values, Exception cases, Input/Output scenarios, and testing different data types. My strategy was to meet the specified requirements while creating tests that facilitate understanding and control of the code's behavior.

# Mock Classes and Mock Helpers:
I introduced MockProducer and MockConsumer as proxy classes to indirectly test the Buffer class. These mock classes, along with BufferHelper and ItemHelper, helped ensure isolated testing and met the requirement to avoid direct testing on Buffer.

# Test Results and Code Coverage:
The test results were positive, with all test cases passing successfully. It's important to note that code coverage met and exceeded the specified requirement of at least 80% (resulting in 100% for classes, methods, and lines), indicating a robust testing strategy.

# Challenges and Solutions:
During the task, I overcame challenges by applying systematic thinking and creative problem-solving skills. Retaining helper classes like BufferHelper and ItemHelper met the requirements set by the instructor, showcasing my ability to adapt to specific demands. The most enjoyable aspect of the task was figuring out how to determine if a thread is in a particular state (State.WAIT) or capturing an exception thrown at a different, indirectly accessible level by intercepting the system's error print handling.

# Improvements and Future Work:
To enhance flexibility and readability, I am considering potential code improvements. These may include exploring additional abstraction and implementing further test cases to improve code maintainability. Primarily, I am thinking of consolidating the interfaces provided by the instructor into a unified interface for future Producer and Consumer classes that, in turn, implement Buffer operations. Additionally, precautions should be taken to prevent, for instance, "null" from being added to the list of items. The Buffer's 'add()' method, unnecessary for printing, can be removed entirely but should not return 'true' regardless of whether an item is added or not. The Buffer class's 'remove()' method returns an item, which can be directly returned from 'buffer.remove()' for cleaner and clearer code.

# Conclusion:
In conclusion, this assignment has been an enlightening experience, allowing me to apply and deepen my understanding of TDD principles. By systematically creating and testing code, I have not only achieved functional results but also strengthened my ability to write testable and robust code.

![tdd_finalProject_coverage](https://github.com/rugsmunny/JAVA22-TDD-slutprojekt-karim-sendesni/assets/49041363/49c808d5-c48a-4a63-b320-2b9d30ffe459)
