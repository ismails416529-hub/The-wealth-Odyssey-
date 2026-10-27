package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DrachmaCoin
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.LaurelGreen
import com.example.ui.theme.SpartanRed
import java.util.Locale
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun CodexScreen(modifier: Modifier = Modifier) {
  var selectedTab by remember { mutableIntStateOf(0) }
  val tabTitles = listOf("Rule of 72", "50/30/20 Tool", "Debt Tactics")

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp)
      .testTag("codex_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "📜 THE ORACLE'S CODEX",
          style = MaterialTheme.typography.labelSmall,
          fontWeight = FontWeight.Bold,
          color = GoldPrimary,
          letterSpacing = 1.sp
        )
        Text(
          text = "Interactive Scrolls of Wealth",
          style = MaterialTheme.typography.headlineSmall,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onBackground
        )
      }
    }

    item {
      TabRow(
        selectedTabIndex = selectedTab,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = GoldPrimary
      ) {
        tabTitles.forEachIndexed { index, title ->
          Tab(
            selected = selectedTab == index,
            onClick = { selectedTab = index },
            text = {
              Text(
                text = title,
                fontFamily = FontFamily.Serif,
                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Medium
              )
            }
          )
        }
      }
    }

    item {
      when (selectedTab) {
        0 -> RuleOf72Calculator()
        1 -> BudgetFiftyThirtyTwentyCalculator()
        2 -> DebtTacticsScroll()
      }
    }

    item {
      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

@Composable
fun RuleOf72Calculator() {
  var interestRate by remember { mutableFloatStateOf(8f) }
  val doublingYears = 72f / interestRate
  val initialCapital = 10000.0

  // 10, 20, 30 year compounding values
  val rateDecimal = (interestRate / 100.0)
  val val10 = initialCapital * (1.0 + rateDecimal).pow(10.0)
  val val20 = initialCapital * (1.0 + rateDecimal).pow(20.0)
  val val30 = initialCapital * (1.0 + rateDecimal).pow(30.0)

  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.4f))
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text = "THE SACRED RULE OF 72",
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = GoldPrimary,
        letterSpacing = 1.sp
      )
      Text(
        text = "Compounding Doubling Speed",
        style = MaterialTheme.typography.titleLarge,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold
      )
      Text(
        text = "Divide 72 by your expected annual interest rate to discover roughly how many years it takes your wealth to double without adding another cent.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      Spacer(modifier = Modifier.height(18.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(text = "Annual Return: ${interestRate.roundToInt()}%", fontWeight = FontWeight.Bold)
        Text(
          text = "Doubles in ~${String.format(Locale.US, "%.1f", doublingYears)} yrs",
          style = MaterialTheme.typography.titleMedium,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = LaurelGreen
        )
      }

      Slider(
        value = interestRate,
        onValueChange = { interestRate = it },
        valueRange = 2f..16f,
        steps = 13,
        colors = SliderDefaults.colors(
          thumbColor = GoldPrimary,
          activeTrackColor = GoldPrimary
        )
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Growth Projection Table for $10,000
      Text(
        text = "Compounding $10,000 at ${interestRate.roundToInt()}% Annual Growth:",
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )
      Spacer(modifier = Modifier.height(8.dp))

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(12.dp))
          .background(MaterialTheme.colorScheme.surfaceVariant)
          .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceAround
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(text = "10 Years", style = MaterialTheme.typography.labelSmall)
          Text(
            text = "$${String.format(Locale.US, "%,d", val10.roundToInt())}",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = GoldPrimary
          )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(text = "20 Years", style = MaterialTheme.typography.labelSmall)
          Text(
            text = "$${String.format(Locale.US, "%,d", val20.roundToInt())}",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = GoldPrimary
          )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(text = "30 Years", style = MaterialTheme.typography.labelSmall)
          Text(
            text = "$${String.format(Locale.US, "%,d", val30.roundToInt())}",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = LaurelGreen
          )
        }
      }
    }
  }
}

@Composable
fun BudgetFiftyThirtyTwentyCalculator() {
  var monthlyIncomeInput by remember { mutableStateOf("3500") }
  val income = monthlyIncomeInput.toDoubleOrNull() ?: 0.0

  val needs = income * 0.50
  val wants = income * 0.30
  val savings = income * 0.20

  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.4f))
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text = "THE 50/30/20 GOLDEN TRIAD",
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = GoldPrimary,
        letterSpacing = 1.sp
      )
      Text(
        text = "Monthly Take-Home Allocator",
        style = MaterialTheme.typography.titleLarge,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold
      )
      Text(
        text = "Enter your net monthly income (after taxes) to calculate your ideal targets.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      Spacer(modifier = Modifier.height(14.dp))

      OutlinedTextField(
        value = monthlyIncomeInput,
        onValueChange = { monthlyIncomeInput = it },
        label = { Text("Monthly Take-Home Income ($)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(16.dp))

      // 3 Buckets
      BudgetBucketRow(
        title = "Needs (50%)",
        amount = needs,
        color = Color(0xFF2980B9),
        examples = "Housing, utilities, groceries, transport, min debt"
      )
      Spacer(modifier = Modifier.height(8.dp))
      BudgetBucketRow(
        title = "Wants (30%)",
        amount = wants,
        color = Color(0xFF8E44AD),
        examples = "Dining out, entertainment, hobbies, streaming, travel"
      )
      Spacer(modifier = Modifier.height(8.dp))
      BudgetBucketRow(
        title = "Savings & Investments (20%)",
        amount = savings,
        color = LaurelGreen,
        examples = "Emergency fund, 401(k), IRA, extra debt paydown"
      )
    }
  }
}

@Composable
fun BudgetBucketRow(
  title: String,
  amount: Double,
  color: Color,
  examples: String
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(10.dp))
      .background(color.copy(alpha = 0.1f))
      .border(1.dp, color.copy(alpha = 0.3f), RoundedCornerShape(10.dp))
      .padding(12.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Column(modifier = Modifier.weight(1f)) {
      Text(text = title, fontWeight = FontWeight.Bold, color = color)
      Text(text = examples, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    Text(
      text = "$${String.format(Locale.US, "%,d", amount.roundToInt())}/mo",
      style = MaterialTheme.typography.titleMedium,
      fontFamily = FontFamily.Serif,
      fontWeight = FontWeight.Bold,
      color = color
    )
  }
}

@Composable
fun DebtTacticsScroll() {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.4f))
  ) {
    Column(
      modifier = Modifier.padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      Text(
        text = "STRAIT OF DEBT TACTICS",
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = GoldPrimary,
        letterSpacing = 1.sp
      )
      Text(
        text = "Avalanche vs. Snowball Comparison",
        style = MaterialTheme.typography.titleLarge,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold
      )

      Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(text = "🏔️ The Debt Avalanche (Mathematical Victory)", fontWeight = FontWeight.Bold, color = GoldPrimary)
          Text(
            text = "Order debts strictly by HIGHEST INTEREST RATE first (e.g. 24% credit card before 5% student loan). Saves the maximum possible dollars in interest charges.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
          )
        }
      }

      Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(text = "☃️ The Debt Snowball (Psychological Victory)", fontWeight = FontWeight.Bold, color = LaurelGreen)
          Text(
            text = "Order debts by SMALLEST BALANCE first regardless of interest rate (e.g. $300 balance before $10,000 balance). Builds rapid dopamine momentum and early wins.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
          )
        }
      }

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(SpartanRed.copy(alpha = 0.12f))
          .padding(10.dp)
      ) {
        Text(
          text = "⚠️ Cardinal Rule: Pay minimum balances on ALL accounts every month to avoid delinquency penalties and credit score destruction.",
          style = MaterialTheme.typography.bodySmall,
          fontWeight = FontWeight.SemiBold,
          color = SpartanRed
        )
      }
    }
  }
}
