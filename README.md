# Online Supermarket Payment Processing System
A Java-based payment system for an online supermarket, supporting multiple payment methods such as card payments, promotions, and loyalty points. This system ensures perpetual transactions and efficient payment processing.

## How It's Made
Tech Used: Java, Maven, Jackson (for JSON handling)
This payment processing system was built using Java, implementing various strategies to support diverse payment methods efficiently. The architecture follows a strategy-based design where payments are processed dynamically based on available methods and order details.

## Optimizations
- Improved payment method selection logic to prevent unnecessary lookup failures.
- Logging enhancements for better debugging visibility. Currently logging statements are all commented out.

## Lessons Learned
- Designing a flexible payment strategy significantly improves scalability.
- Proper exception handling prevents critical transaction failures.
