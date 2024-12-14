### Calculator App Overview

#### Classes and Functions Pseudo Code

**Classes:**
- **`MainActivity`**: The entry point of the application. Manages the lifecycle and sets up the UI.

**Functions:**

1. **`clearAll()`**:
   - Resets all state variables (`resultText`, `currentInput`, `operation`, `firstOperand`, `expression`, `isResultDisplayed`).
   - Ensures a clean slate for new calculations.

2. **`calculateResult()`**:
   - Performs arithmetic operations based on `operation` and the stored operands (`firstOperand`, `currentInput`).
   - Updates `resultText` with the computed result or an error message (e.g., for division by zero).
   - Prepares the app for continued operations if needed.

3. **`handleOperation(op: String)`**:
   - Stores the current operation (`+`, `-`, `*`, `/`) and transitions the app state to accept the next operand.
   - If a result is already displayed, it uses the result as the first operand for the next operation.

4. **`onButtonClick(label: String)`**:
   - Handles input from the calculator buttons (numbers, operations, clear, equals).
   - Routes each button press to the appropriate function (e.g., `clearAll`, `calculateResult`, or `handleOperation`).

5. **`renderUI()` (implicit in Compose UI):**
   - Dynamically updates the display to show the current expression and result.
   - Lays out buttons for numbers and operations in a grid format.

#### State Variables

- **`resultText`**: Displays the current result or input.
- **`currentInput`**: Stores the number currently being entered.
- **`operation`**: Tracks the current arithmetic operation (`+`, `-`, `*`, `/`).
- **`firstOperand`**: Holds the first operand for a calculation.
- **`expression`**: Keeps a string representation of the ongoing operation (e.g., `"1 + "`).
- **`isResultDisplayed`**: Indicates if the last calculation's result is displayed.

#### Application Flow

1. User clicks a button.
2. **`onButtonClick`** determines the type of input (number, operation, equals, clear).
3. Functions like **`handleOperation`** and **`calculateResult`** manage state and perform calculations.
4. UI updates dynamically with the current input, operation, and result.