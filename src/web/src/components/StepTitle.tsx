import { memo } from 'react';

import { cn } from '@/utils';
import Typography from './common/Typography';

interface StepTitleProps {
  title: string;
  description?: string;
  className?: string;
}

function StepTitle({ title, description, className }: StepTitleProps) {
  return (
    <div className={cn('flex flex-col gap-[24px]', className)}>
      <Typography size="headline-3" color="grey-600">
        {title}
      </Typography>
      {description && (
        <Typography size="body-2" color="blueGrey-800">
          {description}
        </Typography>
      )}
    </div>
  );
}

const MemoizedStepTitle = memo(StepTitle);

export default MemoizedStepTitle;
