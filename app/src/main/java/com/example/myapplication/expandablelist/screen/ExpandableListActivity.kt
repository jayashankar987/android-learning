package com.example.myapplication.expandablelist.screen

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.base.AppTheme
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.TopBar
import com.example.myapplication.expandablelist.viewmodel.ExpandableListViewModel
import com.example.myapplication.expandablelist.model.FaqModel

class ExpandableListActivity : BaseActivity() {

    private val viewModel by viewModels<ExpandableListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                mainContent(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun mainContent(viewModel: ExpandableListViewModel) {
    val itemIds by viewModel.itemIds.collectAsState()
    Scaffold(topBar = { TopBar() }) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            itemsIndexed(viewModel.items.value) { index, item ->
                ExpandableContainerView(
                    itemModel = item,
                    onClickItem = { viewModel.onItemClicked(index) },
                    isExpandable = itemIds.contains(index)
                )

            }
        }
    }
}

@Composable
fun ExpandableContainerView(itemModel: FaqModel, onClickItem: () -> Unit, isExpandable: Boolean) {
    Box(modifier = Modifier.background(colorResource(id = R.color.purple_500))) {
        Column {
            QuestionView(questionText = itemModel.question, onClickItem = onClickItem)
            AnswerView(answer = itemModel.answer, isExpandable = isExpandable)
        }
    }
}

@Composable
fun QuestionView(questionText: String, onClickItem: () -> Unit) {
    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.purple_200))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClickItem,
                indication = null
            )
            .padding(8.dp)
    ) {
        Text(text = questionText, fontSize = 18.sp, color = Color.White, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun AnswerView(answer: String, isExpandable: Boolean) {
    val expandTransition = remember {
        expandVertically(expandFrom = Alignment.Top, animationSpec = tween(300)) +
                fadeIn(animationSpec = tween(300))
    }

    val collapseTransition = remember {
        shrinkVertically(shrinkTowards = Alignment.Bottom, animationSpec = tween(300)) +
                fadeOut(animationSpec = tween(300))
    }

    AnimatedVisibility(visible = isExpandable, enter = expandTransition, exit = collapseTransition) {
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.teal_200))
                .padding(15.dp)
        ) {
            Text(text = answer, fontSize = 16.sp, color = Color.Black, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnswerPreview() {
    AnswerView(answer = "Answer", isExpandable = true)
}

@Preview(showBackground = true)
@Composable
fun QuestionViewPreview() {
    QuestionView(questionText = "Question") {

    }
}