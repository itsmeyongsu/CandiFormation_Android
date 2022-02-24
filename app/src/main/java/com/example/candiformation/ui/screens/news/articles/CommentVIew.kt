package com.example.candiformation.ui.screens.news.articles

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.candiformation.models.CommentResponse
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.TimeColor
import com.example.candiformation.ui.theme.VeryLightGrey_type1
import com.example.candiformation.ui.theme.VeryLightGrey_type3

@Composable
fun CommentView(
    commentList: List<CommentResponse>,
    viewModel: SharedViewModel,
    scrollState: ScrollState,
    isDeletable: Boolean
) {
    commentList.forEach { item ->
        CommentViewUnit(
            commentResponse = item,
            viewModel = viewModel,
            isDeletable = isDeletable
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CommentViewUnit(
    commentResponse: CommentResponse,
    viewModel: SharedViewModel,
    isDeletable: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, VeryLightGrey_type1, RoundedCornerShape(8.dp))
            .background(VeryLightGrey_type3)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Text(
                        text = if (commentResponse.isSecret) "익명" else commentResponse.nickname,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = commentResponse.createdAt,
                        color = TimeColor
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {

                    // 댓글 삭제 로직
                    if (commentResponse.nickname == viewModel.currentUser.value.nickname) {
                        if(isDeletable) {
                            Icon(
                                modifier = Modifier
                                    .size(22.dp)
                                    .clickable {
                                        Log.d(
                                            "suee97",
                                            "삭제된 코멘트 아이디 >>> ${commentResponse.id}"
                                        )
                                        viewModel.deleteComment(commentResponse.id)
                                    },
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "",
                                tint = Color.LightGray
                            )
                        }

                    }
                }
            }
            Text(
                text = commentResponse.content
            )
        }
    }
}