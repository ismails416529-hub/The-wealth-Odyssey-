package com.example.data

import com.example.model.BadgeItem
import com.example.model.FinancialTopic
import com.example.model.Island
import com.example.model.IslandNode
import com.example.model.PantheonRival
import com.example.model.QuizQuestion

object QuestionBank {

  val questions: List<QuizQuestion> = listOf(
    // ==========================================
    // ISLAND 1: ISLE OF BUDGETS (BUDGETING)
    // ==========================================
    // Node 1: The 50/30/20 Foundation
    QuizQuestion(
      id = "b1_1",
      topic = FinancialTopic.BUDGETING,
      islandId = 1,
      nodeIndex = 1,
      scenarioContext = "Elena takes home $3,600 per month after taxes. She wants to structure her monthly finances according to the popular 50/30/20 framework.",
      question = "According to the 50/30/20 budgeting rule, what amount should Elena allocate for her 'Needs' (essential living expenses)?",
      options = listOf("$1,800 (50%)", "$1,080 (30%)", "$720 (20%)", "$2,160 (60%)"),
      correctAnswerIndex = 0,
      explanation = "50% of $3,600 equals $1,800. Needs cover must-haves: rent/mortgage, basic groceries, utilities, minimum debt payments, and healthcare.",
      keyTakeaway = "50% for Needs, 30% for Wants, 20% for Savings and Extra Debt Paydown.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "b1_2",
      topic = FinancialTopic.BUDGETING,
      islandId = 1,
      nodeIndex = 1,
      scenarioContext = "Marcus is auditing his monthly expenses to cut down on unnecessary spending.",
      question = "Which of the following belongs in the 'Wants' (30%) category rather than 'Needs' (50%)?",
      options = listOf(
        "Basic grocery staples like rice, vegetables, and milk",
        "Home electrical and water utility bills",
        "Premium streaming TV bundle and dining at restaurants",
        "Mandatory minimum monthly car insurance"
      ),
      correctAnswerIndex = 2,
      explanation = "Entertainment, dining out, fashion upgrades, and streaming subscriptions are discretionary wants. If income drops, wants are the first to adjust.",
      keyTakeaway = "Distinguish true survival needs from lifestyle desires to keep financial flexibility.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "b1_3",
      topic = FinancialTopic.BUDGETING,
      islandId = 1,
      nodeIndex = 1,
      scenarioContext = "Sophia receives a $500 annual bonus and wants to follow the 'Pay Yourself First' principle.",
      question = "What does the 'Pay Yourself First' budgeting habit mean in practice?",
      options = listOf(
        "Buying gifts for yourself before paying bills",
        "Routing a set portion to savings/investments the moment you are paid, before spending",
        "Spending freely all month and saving whatever happens to be left over",
        "Borrowing from credit cards to fund entertainment"
      ),
      correctAnswerIndex = 1,
      explanation = "Paying yourself first means automating savings transfers on payday so wealth accumulation is guaranteed rather than an afterthought.",
      keyTakeaway = "Automate your savings the day your paycheck deposits.",
      drachmaReward = 50
    ),

    // Node 2: The Emergency Anchor
    QuizQuestion(
      id = "b2_1",
      topic = FinancialTopic.BUDGETING,
      islandId = 1,
      nodeIndex = 2,
      scenarioContext = "Lucas has essential living expenses of $2,500 every month.",
      question = "How large should an ideal fully-funded Emergency Fund be for a standard stable earner?",
      options = listOf(
        "$1,000 flat, regardless of income",
        "$7,500 to $15,000 (3 to 6 months of essential living expenses)",
        "1 full year of gross pre-tax salary in physical cash",
        "Zero, relying entirely on available credit card limits"
      ),
      correctAnswerIndex = 1,
      explanation = "Financial planners recommend 3 to 6 months of essential expenses ($7,500 - $15,000 for Lucas) to absorb sudden job loss or medical emergencies.",
      keyTakeaway = "An emergency fund is insurance against life, not an investment.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "b2_2",
      topic = FinancialTopic.BUDGETING,
      islandId = 1,
      nodeIndex = 2,
      scenarioContext = "Nia is deciding where to store her $10,000 emergency fund so it is safe yet productive.",
      question = "Where is the best vehicle to hold your emergency fund?",
      options = listOf(
        "A High-Yield Savings Account (HYSA) with FDIC insurance",
        "Volatile cryptocurrency tokens for high upside",
        "A 5-year locked certificate of deposit with early withdrawal penalties",
        "Individual speculative tech stock options"
      ),
      correctAnswerIndex = 0,
      explanation = "Emergency funds require liquid accessibility and principal preservation. An FDIC-insured HYSA protects your principal while paying competitive interest.",
      keyTakeaway = "Liquidity and capital preservation always trump return for emergency cash.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "b2_3",
      topic = FinancialTopic.BUDGETING,
      islandId = 1,
      nodeIndex = 2,
      scenarioContext = "David's car transmission fails, costing $1,200. He currently has $2,000 in his emergency reserve.",
      question = "What is the correct protocol after using money from your emergency fund?",
      options = listOf(
        "Close the savings account permanently",
        "Pause extra discretionary spending and prioritize replenishing the fund to full capacity",
        "Immediately take a payday loan to double the account balance",
        "Pretend the expense didn't happen and invest the rest in risky assets"
      ),
      correctAnswerIndex = 1,
      explanation = "Emergency funds are meant to be used when crises strike! Once tapped, your top budget priority is refilling the bucket back to 3-6 months.",
      keyTakeaway = "Use the shield when needed, then rebuild the shield immediately.",
      drachmaReward = 50
    ),

    // Node 3: Cashflow & The Silent Thief (Inflation)
    QuizQuestion(
      id = "b3_1",
      topic = FinancialTopic.BUDGETING,
      islandId = 1,
      nodeIndex = 3,
      scenarioContext = "Suppose annual inflation is running at 4% for the year.",
      question = "If you keep $10,000 sitting in a 0% interest checking account for 10 years under 4% inflation, what happens?",
      options = listOf(
        "You will have $14,000 in nominal dollars",
        "The dollar balance stays $10,000, but its purchasing power drops by roughly a third",
        "Your purchasing power doubles due to deflation",
        "The bank takes 50% of the account as a fee"
      ),
      correctAnswerIndex = 1,
      explanation = "Inflation erodes the purchasing power of idle cash. While the nominal number stays $10,000, the goods and services it can buy shrinks steadily.",
      keyTakeaway = "Cash beyond your emergency fund must be invested to outpace inflation.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "b3_2",
      topic = FinancialTopic.BUDGETING,
      islandId = 1,
      nodeIndex = 3,
      scenarioContext = "Maya gets a $10,000 annual raise at work. Shortly after, she leases an expensive luxury car and moves to a pricier condo.",
      question = "What common behavioral financial trap has Maya fallen into?",
      options = listOf(
        "Lifestyle Creep (Lifestyle Inflation)",
        "The Avalanche Debt Method",
        "Dollar-Cost Averaging",
        "Compound Arbitrage"
      ),
      correctAnswerIndex = 0,
      explanation = "Lifestyle creep happens when discretionary consumption expands to match every pay bump, preventing increases in net worth or savings rate.",
      keyTakeaway = "When your income grows, increase your savings rate before expanding your lifestyle.",
      drachmaReward = 50
    ),

    // Node 4 (Boss): The Siren of Impulse Spending
    QuizQuestion(
      id = "b4_1",
      topic = FinancialTopic.BUDGETING,
      islandId = 1,
      nodeIndex = 4,
      scenarioContext = "Boss Trial: Cerberus of the Overdrawn Ledger challenges your financial discipline with 3 rapid scenario dilemmas.",
      question = "Which psychological technique is proven most effective to eliminate impulsive non-essential purchases?",
      options = listOf(
        "Applying the '72-Hour Rule': wait 3 days before buying any non-budgeted want",
        "Immediately charging it to a credit card with reward points",
        "Browsing flash-sale discount countdown timers",
        "Shopping while stressed or hungry"
      ),
      correctAnswerIndex = 0,
      explanation = "The 72-hour delay cools emotional dopamine spikes. Over 70% of impulse wants are forgotten or willingly skipped after 3 days.",
      keyTakeaway = "Introduce friction between impulse desire and checkout.",
      drachmaReward = 80
    ),
    QuizQuestion(
      id = "b4_2",
      topic = FinancialTopic.BUDGETING,
      islandId = 1,
      nodeIndex = 4,
      scenarioContext = "Cerberus Trial 2: A shopper sees a jacket marked down from $200 to $140 and thinks: 'I saved $60!'",
      question = "If the shopper had not planned to buy the jacket at all, what was the actual financial outcome?",
      options = listOf(
        "They grew their net worth by $60",
        "They spent $140 of cash they would have otherwise kept",
        "They earned an annualized 30% dividend return",
        "Their credit score increased by 60 points"
      ),
      correctAnswerIndex = 1,
      explanation = "You never save money by buying something on sale that you didn't need in the first place; you spent $140.",
      keyTakeaway = "A discount on an unneeded item is still an expense, not a saving.",
      drachmaReward = 80
    ),

    // ==========================================
    // ISLAND 2: STRAIT OF DEBT (DEBT MANAGEMENT)
    // ==========================================
    // Node 1: The Hydra of High Interest
    QuizQuestion(
      id = "d1_1",
      topic = FinancialTopic.DEBT_MANAGEMENT,
      islandId = 2,
      nodeIndex = 1,
      scenarioContext = "Jason carries a $5,000 credit card balance at a 24% annual APR. He chooses to pay only the minimum monthly payment (~$120).",
      question = "What will happen if Jason only pays the minimum required monthly amount?",
      options = listOf(
        "The balance will be paid off completely within 12 months",
        "He will pay thousands of dollars in interest and could take over 15-20 years to pay it off",
        "The credit card company waives the interest after 6 months",
        "His credit score will automatically reach 850"
      ),
      correctAnswerIndex = 1,
      explanation = "Minimum payments are designed to mostly cover interest fees with only a tiny sliver toward principal, maximizing the bank's profit over decades.",
      keyTakeaway = "Never carry revolving credit card debt; pay the statement balance in full every month.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "d1_2",
      topic = FinancialTopic.DEBT_MANAGEMENT,
      islandId = 2,
      nodeIndex = 1,
      scenarioContext = "Examining loans: Student Loan A has 4.5% fixed interest. Credit Card B has 26% APR.",
      question = "Why is 26% APR credit card debt considered a 'financial emergency' compared to a 4.5% loan?",
      options = listOf(
        "Credit card debt is illegal in most states",
        "At 26%, compounding interest causes the balance to double every ~2.8 years if unpaid",
        "Low interest loans always require collateral forfeiture",
        "There is no real difference between 4.5% and 26% APR"
      ),
      correctAnswerIndex = 1,
      explanation = "Using the Rule of 72 (72 / 26 ≈ 2.76 years), high-interest revolving debt compounds against you exponentially, devouring future cashflow.",
      keyTakeaway = "Treat high-interest debt (>8-10%) as a financial fire that must be extinguished first.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "d1_3",
      topic = FinancialTopic.DEBT_MANAGEMENT,
      islandId = 2,
      nodeIndex = 1,
      scenarioContext = "Understanding loan terminology: APR vs Nominal Interest Rate.",
      question = "What does APR (Annual Percentage Rate) represent on a loan or credit card?",
      options = listOf(
        "The total annual cost of borrowing, including interest rate plus mandatory loan fees",
        "The monthly rebate check sent to the borrower",
        "The penalty charged only if you declare bankruptcy",
        "The percentage of the loan forgiven by the government each year"
      ),
      correctAnswerIndex = 0,
      explanation = "APR includes both the nominal interest rate and any lender origination fees, providing the true cost comparison between different loan offers.",
      keyTakeaway = "Always compare APR rather than just the advertised base interest rate.",
      drachmaReward = 50
    ),

    // Node 2: Avalanche vs. Snowball
    QuizQuestion(
      id = "d2_1",
      topic = FinancialTopic.DEBT_MANAGEMENT,
      islandId = 2,
      nodeIndex = 2,
      scenarioContext = "Leo has 3 debts: Debt A: $2,000 at 22% APR, Debt B: $8,000 at 6% APR, Debt C: $500 at 14% APR.",
      question = "In the 'Debt Avalanche' method, which debt does Leo tackle first with extra payments?",
      options = listOf(
        "Debt B ($8,000 at 6% APR) because it has the largest balance",
        "Debt A ($2,000 at 22% APR) because it has the highest interest rate",
        "Debt C ($500 at 14% APR) because it is the smallest balance",
        "Split extra cash equally across all three debts"
      ),
      correctAnswerIndex = 1,
      explanation = "The Debt Avalanche targets debts strictly by highest interest rate first, mathematically minimizing total interest paid and clearing debt fastest.",
      keyTakeaway = "Avalanche saves the most money mathematically by killing high interest first.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "d2_2",
      topic = FinancialTopic.DEBT_MANAGEMENT,
      islandId = 2,
      nodeIndex = 2,
      scenarioContext = "Rachel has struggled with motivation while paying off debt. She wants quick psychological wins to build momentum.",
      question = "Which repayment strategy should Rachel choose for behavioral psychological momentum?",
      options = listOf(
        "The Debt Snowball method (pay off the smallest balance first regardless of interest)",
        "The Debt Avalanche method",
        "Taking cash advances from an ATM",
        "Ignoring payments until collections calls"
      ),
      correctAnswerIndex = 0,
      explanation = "The Debt Snowball focuses on paying off the smallest dollar balance first to achieve rapid emotional victories, proven to boost completion rates.",
      keyTakeaway = "The best debt payoff strategy is the one you will actually stick with to the end.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "d2_3",
      topic = FinancialTopic.DEBT_MANAGEMENT,
      islandId = 2,
      nodeIndex = 2,
      scenarioContext = "In both the Snowball and Avalanche debt payoff methods:",
      question = "What must you do regarding the other non-target debts while attacking your priority debt?",
      options = listOf(
        "Completely stop paying anything on the other debts",
        "Pay at least the minimum required payment on all other debts to protect your credit and avoid penalties",
        "Consolidate everything into an unverified offshore account",
        "Default on the oldest loan first"
      ),
      correctAnswerIndex = 1,
      explanation = "You must maintain minimum payments on all accounts to prevent late fees, penalty APR spikes, and severe credit score damage.",
      keyTakeaway = "Pay minimums on everything, then throw all extra cash at your target debt.",
      drachmaReward = 50
    ),

    // Node 3: The Credit Citadel
    QuizQuestion(
      id = "d3_1",
      topic = FinancialTopic.DEBT_MANAGEMENT,
      islandId = 2,
      nodeIndex = 3,
      scenarioContext = "Samantha has a credit card with a total credit limit of $10,000. Her statement balance is currently $6,000.",
      question = "What is Samantha's credit utilization ratio, and how will it likely affect her FICO credit score?",
      options = listOf(
        "60% utilization; it will hurt her score (experts advise keeping utilization under 30%, ideally under 10%)",
        "6% utilization; it is optimal and will boost her score immediately",
        "100% utilization; it has zero impact on credit scoring",
        "Utilization is not factored into credit scores"
      ),
      correctAnswerIndex = 0,
      explanation = "$6,000 / $10,000 = 60%. Credit utilization makes up 30% of your FICO score. High utilization signals financial distress to credit scoring algorithms.",
      keyTakeaway = "Keep revolving credit utilization under 30%—and under 10% for top-tier scores.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "d3_2",
      topic = FinancialTopic.DEBT_MANAGEMENT,
      islandId = 2,
      nodeIndex = 3,
      scenarioContext = "Examining what builds a great credit score:",
      question = "Which single factor carries the largest weight (35%) in calculating your FICO credit score?",
      options = listOf(
        "On-time Payment History",
        "Total number of active credit cards you carry in your wallet",
        "Your current gross annual salary",
        "The balance in your checking account"
      ),
      correctAnswerIndex = 0,
      explanation = "Payment History accounts for 35% of your FICO score. A single 30-day late payment can drop an excellent score by 50 to 100 points.",
      keyTakeaway = "Set up auto-pay for at least the minimum balance so you never miss a due date.",
      drachmaReward = 50
    ),

    // Node 4 (Boss): Minotaur of the Debt Labyrinth
    QuizQuestion(
      id = "d4_1",
      topic = FinancialTopic.DEBT_MANAGEMENT,
      islandId = 2,
      nodeIndex = 4,
      scenarioContext = "Boss Trial: The Minotaur charges! A borrower with $15,000 in credit card debt at 22% receives an offer for a balance transfer card with 0% APR for 18 months, with a 3% transfer fee.",
      question = "Is this balance transfer a smart financial move if the borrower can pay off the entire balance in 15 months?",
      options = listOf(
        "Yes, paying the one-time $450 fee (3%) saves thousands in 22% interest, provided they don't rack up new charges",
        "No, 0% APR promotional offers are always illegal scams",
        "No, because transfer fees compound weekly at 50%",
        "It makes no difference mathematically"
      ),
      correctAnswerIndex = 0,
      explanation = "3% of $15,000 is $450. In contrast, 22% APR on $15,000 costs over $3,000 in interest per year! As long as discipline is kept, it is a massive win.",
      keyTakeaway = "Use 0% balance transfers as a tactical acceleration tool, never as an excuse to spend more.",
      drachmaReward = 80
    ),
    QuizQuestion(
      id = "d4_2",
      topic = FinancialTopic.DEBT_MANAGEMENT,
      islandId = 2,
      nodeIndex = 4,
      scenarioContext = "Minotaur Trial 2: A storefront lender offers a 'quick $500 cash advance until payday' for a '$75 fee for two weeks'.",
      question = "What is the approximate annualized percentage rate (APR) of this common payday loan trap?",
      options = listOf(
        "15% APR",
        "Over 390% APR (75/500 = 15% for 2 weeks, multiplied across 26 periods in a year)",
        "0% APR because it is short term",
        "2% APR"
      ),
      correctAnswerIndex = 1,
      explanation = "Payday loans disguise predatory triple-digit interest as small flat fees ($75 / $500 = 15% for 14 days = 391% APR), creating inescapable debt spirals.",
      keyTakeaway = "Avoid predatory payday loans at all costs; build emergency savings instead.",
      drachmaReward = 80
    ),

    // ==========================================
    // ISLAND 3: THE GOLDEN FLEECE (INVESTING)
    // ==========================================
    // Node 1: The Magic of Compounding (Rule of 72)
    QuizQuestion(
      id = "i1_1",
      topic = FinancialTopic.INVESTING,
      islandId = 3,
      nodeIndex = 1,
      scenarioContext = "Chloe invests $10,000 in an index fund with an expected average annual return of 8%.",
      question = "Using the classic 'Rule of 72', roughly how many years will it take for Chloe's investment to double to $20,000 without adding extra money?",
      options = listOf(
        "9 years (72 divided by 8 = 9)",
        "18 years",
        "72 years",
        "4 years"
      ),
      correctAnswerIndex = 0,
      explanation = "The Rule of 72 states: Divide 72 by the annual rate of return to estimate doubling time. 72 / 8 = 9 years! In 18 years, it doubles again to $40,000.",
      keyTakeaway = "The Rule of 72 is the quickest mental shortcut to measure compounding power.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "i1_2",
      topic = FinancialTopic.INVESTING,
      islandId = 3,
      nodeIndex = 1,
      scenarioContext = "Investor A invests $300/mo from age 20 to 30 (10 years, $36,000 total) then stops completely. Investor B waits until age 30 and invests $300/mo every month until age 60 (30 years, $108,000 total). Both earn 8% returns.",
      question = "Who will generally have more money accumulated by age 60?",
      options = listOf(
        "Investor A, because early compounding has an exponential advantage over adding later capital",
        "Investor B, because they put in 3 times more total cash",
        "Both will have the exact same amount to the dollar",
        "Neither, both balances reset to zero at retirement"
      ),
      correctAnswerIndex = 0,
      explanation = "Because Investor A gave their money an extra decade to compound, their earlier gains compound on themselves, often surpassing those who started late despite investing 3x less principal!",
      keyTakeaway = "Time in the market is the greatest wealth amplifier; start early, even with modest amounts.",
      drachmaReward = 50
    ),

    // Node 2: The Shield of Diversification
    QuizQuestion(
      id = "i2_1",
      topic = FinancialTopic.INVESTING,
      islandId = 3,
      nodeIndex = 2,
      scenarioContext = "A beginner investor is considering putting their entire $20,000 life savings into a single hot tech stock they saw on social media.",
      question = "What is the primary danger of concentrating all your capital into an individual stock instead of an index fund?",
      options = listOf(
        "Single-company risk (idiosyncratic risk): if the company faces fraud, competition, or bankruptcy, your savings can be wiped out",
        "Individual stocks never pay dividends",
        "Single stocks are taxed at a 90% federal rate",
        "There is no risk; single stocks always outperform diversified funds"
      ),
      correctAnswerIndex = 0,
      explanation = "Individual companies can fail or underperform. A broad index fund (like the S&P 500 or Total World Index) spreads your capital across hundreds or thousands of enterprises.",
      keyTakeaway = "Diversification is the only 'free lunch' in investing: higher stability without reducing expected market return.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "i2_2",
      topic = FinancialTopic.INVESTING,
      islandId = 3,
      nodeIndex = 2,
      scenarioContext = "Comparing mutual funds and ETFs: Fund X charges an expense ratio of 1.5% annually. Index Fund Y charges 0.03% annually.",
      question = "Over a 30-year investing career, how much does a 1.5% fee impact your final portfolio value?",
      options = listOf(
        "It can eat away 30% to 40% or more of your total potential retirement nest egg",
        "It only costs about $5 total over your entire lifetime",
        "High-fee funds are legally guaranteed to produce higher profits",
        "Fees do not affect investment performance"
      ),
      correctAnswerIndex = 0,
      explanation = "Fees compound just like returns! A 1.5% fee deducted every single year drastically dampens exponential growth, siphoning off hundreds of thousands over decades.",
      keyTakeaway = "Keep investment expense ratios ultra-low (under 0.10% for broad index funds).",
      drachmaReward = 50
    ),

    // Node 3: The Tax-Advantaged Vessels
    QuizQuestion(
      id = "i3_1",
      topic = FinancialTopic.INVESTING,
      islandId = 3,
      nodeIndex = 3,
      scenarioContext = "Zack's employer offers a 401(k) retirement plan with a 100% dollar-for-dollar match on up to 4% of his salary.",
      question = "What is the return on investment (ROI) Zack gets immediately by contributing that 4% to get the company match?",
      options = listOf(
        "An instant 100% guaranteed return on his money",
        "A standard 4% return",
        "Zero return until age 75",
        "A negative return due to matching fees"
      ),
      correctAnswerIndex = 0,
      explanation = "A dollar-for-dollar employer match is an instantaneous 100% return on your contributed cash—essentially free money. Never leave employer match on the table!",
      keyTakeaway = "Always contribute enough to your employer retirement plan to capture the full company match.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "i3_2",
      topic = FinancialTopic.INVESTING,
      islandId = 3,
      nodeIndex = 3,
      scenarioContext = "Choosing between a Traditional IRA and a Roth IRA for retirement savings.",
      question = "What is the defining tax advantage of a Roth IRA?",
      options = listOf(
        "You contribute after-tax money today, and all investment growth and qualified withdrawals in retirement are 100% tax-free",
        "You get a tax deduction today, but pay income taxes on all withdrawals when retired",
        "You never have to invest the money; the government doubles it automatically",
        "You can only withdraw money if you have a doctor's permission"
      ),
      correctAnswerIndex = 0,
      explanation = "Roth IRAs are funded with after-tax dollars. Once inside, dividends and capital gains compound completely tax-free, and eligible withdrawals after age 59½ owe zero income tax.",
      keyTakeaway = "Roth IRAs provide incredible tax-free compounding, especially valuable when you are in a lower tax bracket early in life.",
      drachmaReward = 50
    ),
    QuizQuestion(
      id = "i3_3",
      topic = FinancialTopic.INVESTING,
      islandId = 3,
      nodeIndex = 3,
      scenarioContext = "Taylor sets up an automated transfer of $250 every month into an index fund, regardless of whether the market goes up or down.",
      question = "What investing discipline is Taylor practicing?",
      options = listOf(
        "Dollar-Cost Averaging (DCA)",
        "Market Timing",
        "High-Frequency Day Trading",
        "Penny Stock Arbitrage"
      ),
      correctAnswerIndex = 0,
      explanation = "Dollar-Cost Averaging removes emotional guesswork. When prices drop, your fixed $250 buys more shares; when prices rise, it buys fewer shares, smoothing out your average cost.",
      keyTakeaway = "Automated Dollar-Cost Averaging beats emotional market timing for long-term wealth.",
      drachmaReward = 50
    ),

    // Node 4 (Boss): The Sphinx of Capital Growth
    QuizQuestion(
      id = "i4_1",
      topic = FinancialTopic.INVESTING,
      islandId = 3,
      nodeIndex = 4,
      scenarioContext = "Boss Trial: The Sphinx challenges your emotional fortitude. A sudden global recession causes the stock market to drop 25% in 2 months. You have 25 years until retirement.",
      question = "What is the historically optimal strategy during a major market downturn for a long-term investor?",
      options = listOf(
        "Stay the course, continue regular contributions, and view lower prices as buying quality companies on sale",
        "Panic and sell all your investments to cash, locking in the 25% loss",
        "Stop all investments and hide cash in a mattress",
        "Borrow money at 30% APR to short the market"
      ),
      correctAnswerIndex = 0,
      explanation = "Market drops are normal, expected features of equity investing. Selling during a drop locks in temporary paper losses. Continuing your plan buys shares at discount valuations.",
      keyTakeaway = "Emotional discipline during market downturns is what separates wealthy investors from speculators.",
      drachmaReward = 80
    ),

    // ==========================================
    // ISLAND 4: MOUNT OLYMPUS (GRAND BOSS CRUCIBLE)
    // ==========================================
    QuizQuestion(
      id = "o1_1",
      topic = FinancialTopic.BOSS_CHALLENGE,
      islandId = 4,
      nodeIndex = 1,
      scenarioContext = "Zeus's Crucible: You inherit $10,000. You have $4,000 in credit card debt at 24% APR, zero emergency savings, and your employer offers an unmatched 401(k).",
      question = "What is the most financially optimal order of operations for this $10,000?",
      options = listOf(
        "1. Eliminate the 24% credit card debt ($4,000), 2. Seed a $3,000-$5,000 starter emergency fund in an HYSA, 3. Invest the remainder",
        "Invest the entire $10,000 into speculative high-risk stocks while letting the 24% debt grow",
        "Spend the $10,000 on a luxury vacation because life is short",
        "Pay only the minimum on the credit card and keep all $10,000 in a 0% checking account"
      ),
      correctAnswerIndex = 0,
      explanation = "Guaranteed 24% return by wiping out the credit card debt, combined with building an emergency cushion to avoid new debt, establishes an unbreakable financial foundation.",
      keyTakeaway = "Financial order of operations: Eliminate toxic high-interest debt, build emergency cushion, then invest aggressively.",
      drachmaReward = 100
    ),
    QuizQuestion(
      id = "o1_2",
      topic = FinancialTopic.BOSS_CHALLENGE,
      islandId = 4,
      nodeIndex = 1,
      scenarioContext = "Zeus's Crucible 2: Net Worth Calculation dilemma.",
      question = "How is an individual's financial Net Worth calculated?",
      options = listOf(
        "Total Assets (what you own: cash, investments, home value) MINUS Total Liabilities (what you owe: mortgages, loans, credit debt)",
        "Your total monthly gross salary multiplied by your age",
        "The total credit limit available across all your credit cards",
        "Total money spent over the course of the previous calendar year"
      ),
      correctAnswerIndex = 0,
      explanation = "Net Worth = Assets minus Liabilities. It is the purest snapshot of your overall financial health, distinct from merely how much you earn.",
      keyTakeaway = "Focus on growing your Net Worth (Assets - Debts), not just your gross income.",
      drachmaReward = 100
    ),
    QuizQuestion(
      id = "o1_3",
      topic = FinancialTopic.BOSS_CHALLENGE,
      islandId = 4,
      nodeIndex = 1,
      scenarioContext = "Zeus's Crucible 3: The Ultimate Principle of Wealth.",
      question = "What is the single most enduring secret to building long-term financial independence?",
      options = listOf(
        "Consistently spending less than you earn, investing the difference systematically, and allowing compound interest time to work",
        "Timing the exact top and bottom of volatile speculative markets",
        "Borrowing as much money as lenders permit to display outward luxury",
        "Winning a state lottery or finding sunken treasure"
      ),
      correctAnswerIndex = 0,
      explanation = "Wealth is what you do not see: the gap between income and lifestyle, captured in appreciating assets over time. Simplicity, discipline, and patience build empires.",
      keyTakeaway = "Live below your means, invest the surplus, and let compounding time do the heavy lifting.",
      drachmaReward = 100
    )
  )

  fun getIslands(): List<Island> {
    val island1Nodes = listOf(
      IslandNode(
        id = "isle_1_node_1",
        islandId = 1,
        nodeIndex = 1,
        title = "The 50/30/20 Foundation",
        subtitle = "Master Needs, Wants, and Savings Splits",
        topic = FinancialTopic.BUDGETING,
        isBoss = false,
        questions = questions.filter { it.islandId == 1 && it.nodeIndex == 1 }
      ),
      IslandNode(
        id = "isle_1_node_2",
        islandId = 1,
        nodeIndex = 2,
        title = "The Emergency Anchor",
        subtitle = "Building 3-6 Months of Shielding Reserves",
        topic = FinancialTopic.BUDGETING,
        isBoss = false,
        questions = questions.filter { it.islandId == 1 && it.nodeIndex == 2 }
      ),
      IslandNode(
        id = "isle_1_node_3",
        islandId = 1,
        nodeIndex = 3,
        title = "Cashflow & The Silent Thief",
        subtitle = "Defeating Inflation & Lifestyle Creep",
        topic = FinancialTopic.BUDGETING,
        isBoss = false,
        questions = questions.filter { it.islandId == 1 && it.nodeIndex == 3 }
      ),
      IslandNode(
        id = "isle_1_node_4",
        islandId = 1,
        nodeIndex = 4,
        title = "The Siren of Impulse Spending",
        subtitle = "Cerberus Guardian Boss Trial",
        topic = FinancialTopic.BUDGETING,
        isBoss = true,
        bossName = "Cerberus of Overspending",
        questions = questions.filter { it.islandId == 1 && it.nodeIndex == 4 }
      )
    )

    val island2Nodes = listOf(
      IslandNode(
        id = "isle_2_node_1",
        islandId = 2,
        nodeIndex = 1,
        title = "The Hydra of High Interest",
        subtitle = "Understanding APR and Minimum Payment Traps",
        topic = FinancialTopic.DEBT_MANAGEMENT,
        isBoss = false,
        questions = questions.filter { it.islandId == 2 && it.nodeIndex == 1 }
      ),
      IslandNode(
        id = "isle_2_node_2",
        islandId = 2,
        nodeIndex = 2,
        title = "Avalanche vs. Snowball",
        subtitle = "Mathematical vs. Psychological Strategies",
        topic = FinancialTopic.DEBT_MANAGEMENT,
        isBoss = false,
        questions = questions.filter { it.islandId == 2 && it.nodeIndex == 2 }
      ),
      IslandNode(
        id = "isle_2_node_3",
        islandId = 2,
        nodeIndex = 3,
        title = "The Credit Citadel",
        subtitle = "Mastering FICO Factors and Utilization Ratios",
        topic = FinancialTopic.DEBT_MANAGEMENT,
        isBoss = false,
        questions = questions.filter { it.islandId == 2 && it.nodeIndex == 3 }
      ),
      IslandNode(
        id = "isle_2_node_4",
        islandId = 2,
        nodeIndex = 4,
        title = "Minotaur of the Debt Labyrinth",
        subtitle = "Guardian Boss Trial on Loan Traps",
        topic = FinancialTopic.DEBT_MANAGEMENT,
        isBoss = true,
        bossName = "Minotaur of Compounding Debt",
        questions = questions.filter { it.islandId == 2 && it.nodeIndex == 4 }
      )
    )

    val island3Nodes = listOf(
      IslandNode(
        id = "isle_3_node_1",
        islandId = 3,
        nodeIndex = 1,
        title = "The Magic of Compounding",
        subtitle = "The Rule of 72 and Exponential Growth",
        topic = FinancialTopic.INVESTING,
        isBoss = false,
        questions = questions.filter { it.islandId == 3 && it.nodeIndex == 1 }
      ),
      IslandNode(
        id = "isle_3_node_2",
        islandId = 3,
        nodeIndex = 2,
        title = "The Shield of Diversification",
        subtitle = "Index Funds vs. Single-Stock Vulnerability",
        topic = FinancialTopic.INVESTING,
        isBoss = false,
        questions = questions.filter { it.islandId == 3 && it.nodeIndex == 2 }
      ),
      IslandNode(
        id = "isle_3_node_3",
        islandId = 3,
        nodeIndex = 3,
        title = "Tax-Advantaged Vessels",
        subtitle = "401(k) Matching, Roth IRAs, and DCA",
        topic = FinancialTopic.INVESTING,
        isBoss = false,
        questions = questions.filter { it.islandId == 3 && it.nodeIndex == 3 }
      ),
      IslandNode(
        id = "isle_3_node_4",
        islandId = 3,
        nodeIndex = 4,
        title = "The Sphinx of Capital Growth",
        subtitle = "Guardian Boss Trial on Market Volatility",
        topic = FinancialTopic.INVESTING,
        isBoss = true,
        bossName = "The Sphinx of Market Tempests",
        questions = questions.filter { it.islandId == 3 && it.nodeIndex == 4 }
      )
    )

    val island4Nodes = listOf(
      IslandNode(
        id = "isle_4_node_1",
        islandId = 4,
        nodeIndex = 1,
        title = "Zeus's Pantheon Crucible",
        subtitle = "The Grand Olympus Financial Master Trial",
        topic = FinancialTopic.BOSS_CHALLENGE,
        isBoss = true,
        bossName = "Zeus the Sovereign of Wealth",
        questions = questions.filter { it.islandId == 4 && it.nodeIndex == 1 }
      )
    )

    return listOf(
      Island(
        id = 1,
        name = "Isle of Budgets",
        greekTitle = "The Oasis of Ithaca",
        description = "Lay the bedrock of financial freedom. Master cashflow, the 50/30/20 division, and anchor emergency reserves against storm seas.",
        topic = FinancialTopic.BUDGETING,
        requiredStarsToUnlock = 0,
        nodes = island1Nodes
      ),
      Island(
        id = 2,
        name = "Strait of Debt",
        greekTitle = "The Trials of Scylla & Charybdis",
        description = "Navigate perilous whirlpools of high-interest credit cards, slay compounding debt traps, and fortify your credit citadel.",
        topic = FinancialTopic.DEBT_MANAGEMENT,
        requiredStarsToUnlock = 6,
        nodes = island2Nodes
      ),
      Island(
        id = 3,
        name = "The Golden Fleece",
        greekTitle = "The Mount Olympus Assets",
        description = "Harness the god-like power of exponential compounding. Deploy index fund shields, Roth IRA vessels, and dollar-cost averaging.",
        topic = FinancialTopic.INVESTING,
        requiredStarsToUnlock = 14,
        nodes = island3Nodes
      ),
      Island(
        id = 4,
        name = "Mount Olympus Gate",
        greekTitle = "The Sovereign Crucible",
        description = "The ultimate test of financial mastery. Prove your wisdom across all disciplines to claim your seat in the Pantheon.",
        topic = FinancialTopic.BOSS_CHALLENGE,
        requiredStarsToUnlock = 22,
        nodes = island4Nodes
      )
    )
  }

  val initialBadges: List<BadgeItem> = listOf(
    BadgeItem(
      id = "badge_first_voyage",
      title = "First Oar Stroke",
      greekTitle = "Protos Plous",
      description = "Completed your very first financial trial on the Aegean sea.",
      category = "Voyage",
      emojiIcon = "🚣"
    ),
    BadgeItem(
      id = "badge_budget_master",
      title = "Spartan Thrifter",
      greekTitle = "Sophron Ithakis",
      description = "Conquered all trials on the Isle of Budgets.",
      category = "Budgeting",
      emojiIcon = "⚖️"
    ),
    BadgeItem(
      id = "badge_debt_slayer",
      title = "Slayer of Scylla",
      greekTitle = "Thrasys Scyllas",
      description = "Defeated the high-interest debt traps and conquered the Debt Boss.",
      category = "Debt",
      emojiIcon = "🛡️"
    ),
    BadgeItem(
      id = "badge_golden_fleece",
      title = "Argonaut of Compounding",
      greekTitle = "Chrysomallon Deras",
      description = "Mastered the Rule of 72, index funds, and the Golden Fleece of investing.",
      category = "Investing",
      emojiIcon = "🌱"
    ),
    BadgeItem(
      id = "badge_laurel_collector",
      title = "Olympian Laurel Crown",
      greekTitle = "Daphnestefanos",
      description = "Earned 15 or more golden laurels across your voyage.",
      category = "Mastery",
      emojiIcon = "👑"
    ),
    BadgeItem(
      id = "badge_drachma_hoard",
      title = "Treasury of Croesus",
      greekTitle = "Kroisos Thesauros",
      description = "Amassed 1,000 or more golden Drachmas in your coffer.",
      category = "Wealth",
      emojiIcon = "💰"
    ),
    BadgeItem(
      id = "badge_pantheon_ascendant",
      title = "Titan of Capital",
      greekTitle = "Titan Ploutou",
      description = "Conquered Zeus's Crucible on Mount Olympus.",
      category = "Legendary",
      emojiIcon = "⚡"
    )
  )

  val basePantheonRivals: List<PantheonRival> = listOf(
    PantheonRival(
      rank = 1,
      name = "King Midas",
      title = "Titan of Gold Touch",
      drachmas = 3450,
      stars = 27,
      symbol = "👑",
      epithet = "Turned every expense into a compounding dividend."
    ),
    PantheonRival(
      rank = 2,
      name = "Athena of Wisdom",
      title = "Goddess of Prudent Strategy",
      drachmas = 2980,
      stars = 25,
      symbol = "🦉",
      epithet = "Never pays interest; builds impenetrable reserves."
    ),
    PantheonRival(
      rank = 3,
      name = "Hermes the Swift",
      title = "Messenger of Liquid Cashflows",
      drachmas = 2420,
      stars = 22,
      symbol = "🪽",
      epithet = "Automates dollar-cost averaging on the 1st of every month."
    ),
    PantheonRival(
      rank = 4,
      name = "Odysseus the Prudent",
      title = "Hero of the Patient Voyage",
      drachmas = 1890,
      stars = 19,
      symbol = "⛵",
      epithet = "Tied himself to the mast to resist flash-sale sirens."
    ),
    PantheonRival(
      rank = 5,
      name = "Hephaestus the Builder",
      title = "Architect of the Emergency Shield",
      drachmas = 1520,
      stars = 16,
      symbol = "⚒️",
      epithet = "Forged a 6-month fortress in high-yield reserves."
    ),
    PantheonRival(
      rank = 6,
      name = "Daedalus the Investor",
      title = "Master of Balanced Allocations",
      drachmas = 1150,
      stars = 13,
      symbol = "📐",
      epithet = "Flew between the sun of stocks and the sea of bonds."
    ),
    PantheonRival(
      rank = 7,
      name = "Jason the Argonaut",
      title = "Seeker of the Golden Fleece",
      drachmas = 840,
      stars = 10,
      symbol = "🐏",
      epithet = "Harnessed low-cost index funds to sail uncharted markets."
    ),
    PantheonRival(
      rank = 8,
      name = "Penelope the Patient",
      title = "Weaver of Compound Fortunes",
      drachmas = 620,
      stars = 8,
      symbol = "🧵",
      epithet = "Steadily compounds wealth with unwavering discipline."
    )
  )
}
