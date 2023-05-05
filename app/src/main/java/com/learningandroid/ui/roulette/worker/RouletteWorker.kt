package com.learningandroid.ui.roulette.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.learningandroid.model.repository.RouletteRepository
import com.learningandroid.ui.roulette.response.DeleteAllStatus
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class RouletteWorker  @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: RouletteRepository
) : CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME = "com.learningandroid.ui.roulette.worker.RouletteWorker"
    }

    override suspend fun doWork(): Result {
        Timber.d("RouletteWorker doWork")

        return when (repository.deleteAllRouletteInfo()) {
            DeleteAllStatus.Success -> {
                Timber.d("RouletteWorker delete success")
                Result.success()
            }
            DeleteAllStatus.Failed -> {
                Timber.d("RouletteWorker delete failed")
                Result.failure()
            }
        }
    }

}