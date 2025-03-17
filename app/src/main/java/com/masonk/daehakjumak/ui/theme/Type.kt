package com.masonk.daehakjumak.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.masonk.daehakjumak.R

// Pretendard FontFamily
val PretendardFontFamily = FontFamily(
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold)
)

// Typography
val Typography = Typography(
    displayLarge = TextStyle( // H1
        fontFamily = PretendardFontFamily,
        fontSize = 44.sp,
        fontWeight = FontWeight.Bold // 해당 FontFamily에서 Bold에 매핑된 pretendard_bold 적용
    ),
    displayMedium = TextStyle( // H2
        fontFamily = PretendardFontFamily,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold
    ),
    displaySmall = TextStyle( // H3
        fontFamily = PretendardFontFamily,
        fontSize = 36.sp,
        fontWeight = FontWeight.Medium
    ),
    headlineLarge = TextStyle( // H4
        fontFamily = PretendardFontFamily,
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold
    ),
    headlineMedium = TextStyle( // Subtitle1
        fontFamily = PretendardFontFamily,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    ),
    headlineSmall = TextStyle( // Subtitle2
        fontFamily = PretendardFontFamily,
        fontSize = 30.sp,
        fontWeight = FontWeight.Medium
    ),
    titleLarge = TextStyle( // Subtitle3
        fontFamily = PretendardFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleMedium = TextStyle( // Subtitle4
        fontFamily = PretendardFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.Medium
    ),
    titleSmall = TextStyle( // Subtitle5
        fontFamily = PretendardFontFamily,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = TextStyle( // Body1
        fontFamily = PretendardFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyMedium = TextStyle( // Body2
        fontFamily = PretendardFontFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    ),
    bodySmall = TextStyle( // Body3
        fontFamily = PretendardFontFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium
    ),
    labelLarge = TextStyle( // Body4
        fontFamily = PretendardFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium
    ),
    labelMedium = TextStyle( // Navigation rail
        fontFamily = PretendardFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    ),
    labelSmall = TextStyle( // Tool tip
        fontFamily = PretendardFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )
)

